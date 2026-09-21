package com.utec.tropelcare.service;

import com.utec.tropelcare.dto.*; import com.utec.tropelcare.entity.*; import com.utec.tropelcare.event.TropelSignalCreatedEvent; import com.utec.tropelcare.exception.*; import com.utec.tropelcare.repository.*;
import org.springframework.context.ApplicationEventPublisher; import org.springframework.data.domain.*; import org.springframework.data.jpa.domain.Specification; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.time.*; import java.util.*;

@Service
public class SignalService {
 private final TropelSignalRepository repo; private final TropelRepository tropels; private final SectorRepository sectors; private final GuardianRepository guardians; private final CareResponseRepository care; private final AiClassifierService ai; private final ApplicationEventPublisher publisher;
 private static final Map<String,String> CODES=Map.of("HAMBRE","DISPATCH_NUTRIENT_PACK","ABANDONO","SEND_COMPANIONSHIP_PROTOCOL","MUTACION","ISOLATE_AND_OBSERVE","FUGA","ACTIVATE_SECTOR_LOCK","CONFLICTO","DEPLOY_MEDIATION_FIELD","REPRODUCCION_MASIVA","ENABLE_POPULATION_CONTROL","SENAL_CORRUPTA","ARCHIVE_AND_IGNORE");
 public SignalService(TropelSignalRepository r,TropelRepository t,SectorRepository sec,GuardianRepository g,CareResponseRepository c,AiClassifierService a,ApplicationEventPublisher p){repo=r;tropels=t;sectors=sec;guardians=g;care=c;ai=a;publisher=p;}
 @Transactional public SignalDto create(SignalRequest r){
   Tropel t=tropels.findById(r.tropelId()).orElseThrow(()->new NotFoundException("No existe un Tropel con id "+r.tropelId()));
   Guardian g=guardians.findById(r.guardianId()).orElseThrow(()->new NotFoundException("No existe un guardián con id "+r.guardianId()));
   if(!t.getGuardian().getId().equals(g.getId()))throw new BusinessException("El guardianId no corresponde al guardián responsable de este Tropel");
   TropelSignal s=new TropelSignal();s.setTropel(t);s.setGuardian(g);s.setSenderTag(r.senderTag());s.setRawContent(r.rawContent());s.setCreatedAt(Instant.now());s.setUpdatedAt(s.getCreatedAt());
   AiClassifierService.Classification c;
   try{c=ai.classify(r.rawContent()); applySuccess(t,c); s.setSignalType(c.signalType());s.setSeverity(c.severity());s.setAssignedUnit(c.assignedUnit());s.setRecommendedAction(c.recommendedAction());s.setPersonalityNote(c.personalityNote());s.setStatus("RECIBIDA");}
   catch(Exception ex){c=AiClassifierService.fallback();s.setSignalType(c.signalType());s.setSeverity(c.severity());s.setAssignedUnit(c.assignedUnit());s.setRecommendedAction(c.recommendedAction());s.setStatus("ERROR");}
   s=repo.save(s);
   CareResponse cr=new CareResponse();cr.setSignal(s);cr.setResponseCode(CODES.get(s.getSignalType()));cr.setDescription(s.getRecommendedAction());cr.setCreatedAt(Instant.now());care.save(cr);
   if("RECIBIDA".equals(s.getStatus())) publisher.publishEvent(new TropelSignalCreatedEvent(s.getId()));
   return dto(s);
 }
 private void applySuccess(Tropel t,AiClassifierService.Classification c){
   int e=t.getEnergyLevel(),ch=t.getChaosIndex(),m=t.getMutationStage();
   switch(c.severity()){case "LEVE"-> {e-=5;ch+=5;} case "MODERADO"->{e-=10;ch+=15;} case "GRAVE"->{e-=20;ch+=30;} case "CRITICO"->{e-=30;ch+=45;m+=1;} }
   t.setEnergyLevel(Math.max(0,Math.min(100,e)));t.setChaosIndex(Math.max(0,Math.min(100,ch)));t.setMutationStage(Math.max(0,Math.min(5,m)));
   if(t.getChaosIndex()>=80)t.setVitalState("CRITICO"); else if(t.getEnergyLevel()<=20)t.setVitalState("HAMBRIENTO"); else if("CRITICO".equals(c.severity()))t.setVitalState("MUTANDO"); else if("GRAVE".equals(c.severity()))t.setVitalState("AGITADO");
   t.setUpdatedAt(Instant.now()); Sector s=t.getSector(); if("FUGA".equals(c.signalType()))s.setStabilityLevel(Math.max(0,s.getStabilityLevel()-10)); else if("REPRODUCCION_MASIVA".equals(c.signalType()))s.setStabilityLevel(Math.max(0,s.getStabilityLevel()-15));
   tropels.save(t); sectors.save(s);
 }
 @Transactional(readOnly=true) public SignalDto get(Long id){return dto(repo.findById(id).orElseThrow(()->new NotFoundException("No existe una señal con id "+id)));}
 @Transactional(readOnly=true) public PageResponse<SignalDto> search(String type,String severity,String status,Long tropelId,Long guardianId,LocalDate from,LocalDate to,int page,int size){
   Pageable p=PageRequest.of(Math.max(0,page),Math.min(Math.max(1,size),100),Sort.by("id").descending());Specification<TropelSignal> s=Specification.where(null);
   if(type!=null)s=s.and((r,q,c)->c.equal(r.get("signalType"),type));if(severity!=null)s=s.and((r,q,c)->c.equal(r.get("severity"),severity));if(status!=null)s=s.and((r,q,c)->c.equal(r.get("status"),status));if(tropelId!=null)s=s.and((r,q,c)->c.equal(r.get("tropel").get("id"),tropelId));if(guardianId!=null)s=s.and((r,q,c)->c.equal(r.get("guardian").get("id"),guardianId));if(from!=null)s=s.and((r,q,c)->c.greaterThanOrEqualTo(r.get("createdAt"),from.atStartOfDay().toInstant(ZoneOffset.UTC)));if(to!=null)s=s.and((r,q,c)->c.lessThan(r.get("createdAt"),to.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC)));
   Page<TropelSignal> result=repo.findAll(s,p);return new PageResponse<>(result.getContent().stream().map(this::dto).toList(),result.getTotalElements(),result.getTotalPages(),result.getNumber(),result.getSize());
 }
 private SignalDto dto(TropelSignal s){return new SignalDto(s.getId(),s.getTropel().getId(),s.getTropel().getName(),s.getGuardian().getId(),s.getGuardian().getDisplayName(),s.getSenderTag(),s.getRawContent(),s.getSignalType(),s.getSeverity(),s.getAssignedUnit(),s.getRecommendedAction(),s.getStatus(),s.getCreatedAt(),s.getUpdatedAt());}
}
