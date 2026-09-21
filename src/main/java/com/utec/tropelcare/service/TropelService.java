package com.utec.tropelcare.service;

import com.utec.tropelcare.dto.*;
import com.utec.tropelcare.entity.*;
import com.utec.tropelcare.exception.*;
import com.utec.tropelcare.repository.*;
import org.springframework.data.domain.*; import org.springframework.data.jpa.domain.Specification; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
public class TropelService {
 private final TropelRepository repo; private final SectorRepository sectors; private final GuardianRepository guardians;
 public TropelService(TropelRepository r,SectorRepository s,GuardianRepository g){repo=r;sectors=s;guardians=g;}
 @Transactional public TropelDto create(TropelRequest r){
   if(repo.findByName(r.name()).isPresent())throw new ConflictException("El nombre del Tropel ya existe");
   if(!Setups.SPECIES.contains(r.species()))throw new BusinessException("species inválida");
   Sector s=sectors.findById(r.sectorId()).orElseThrow(()->new NotFoundException("No existe un sector con id "+r.sectorId()));
   Guardian g=guardians.findById(r.guardianId()).orElseThrow(()->new NotFoundException("No existe un guardián con id "+r.guardianId()));
   if(s.getCurrentLoad()>=s.getCapacity())throw new BusinessException("El sector está lleno");
   Tropel t=new Tropel();t.setName(r.name());t.setSpecies(r.species());t.setVitalState("ESTABLE");t.setEnergyLevel(80);t.setChaosIndex(10);t.setMutationStage(0);t.setSector(s);t.setGuardian(g);t.setCreatedAt(Instant.now());t.setUpdatedAt(t.getCreatedAt());
   s.setCurrentLoad(s.getCurrentLoad()+1);sectors.save(s);return dto(repo.save(t));
 }
 @org.springframework.transaction.annotation.Transactional(readOnly=true) public TropelDto get(Long id){return dto(repo.findById(id).orElseThrow(()->new NotFoundException("No existe un Tropel con id "+id)));}
 @org.springframework.transaction.annotation.Transactional(readOnly=true) public PageResponse<TropelDto> search(String species,String vitalState,Long sectorId,Long guardianId,int page,int size){
   Pageable p=PageRequest.of(Math.max(0,page),Math.min(Math.max(1,size),100),Sort.by("id").ascending()); Specification<Tropel> s=Specification.where(null);
   if(species!=null)s=s.and((root,q,c)->c.equal(root.get("species"),species)); if(vitalState!=null)s=s.and((root,q,c)->c.equal(root.get("vitalState"),vitalState)); if(sectorId!=null)s=s.and((root,q,c)->c.equal(root.get("sector").get("id"),sectorId)); if(guardianId!=null)s=s.and((root,q,c)->c.equal(root.get("guardian").get("id"),guardianId));
   Page<Tropel> result=repo.findAll(s,p);return new PageResponse<>(result.getContent().stream().map(this::dto).toList(),result.getTotalElements(),result.getTotalPages(),result.getNumber(),result.getSize());
 }
 @org.springframework.transaction.annotation.Transactional(readOnly=true) public DiaryDto diary(Long id){Tropel t=repo.findById(id).orElseThrow(()->new NotFoundException("No existe un Tropel con id "+id));return new DiaryDto(t.getId(),t.getName(),t.getSignals().stream().filter(x->x.getPersonalityNote()!=null&&!x.getPersonalityNote().isBlank()).sorted((a,b)->b.getCreatedAt().compareTo(a.getCreatedAt())).map(x->new DiaryDto.NoteDto(x.getId(),x.getPersonalityNote(),x.getCreatedAt())).toList());}
 private TropelDto dto(Tropel t){return new TropelDto(t.getId(),t.getName(),t.getSpecies(),t.getVitalState(),t.getEnergyLevel(),t.getChaosIndex(),t.getMutationStage(),t.getSector().getId(),t.getSector().getSectorCode(),t.getGuardian().getId(),t.getGuardian().getDisplayName(),t.getCreatedAt(),t.getUpdatedAt());}
}
