package com.utec.tropelcare.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tropel_signals")
public class TropelSignal {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="tropel_id", nullable=false) private Tropel tropel;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="guardian_id", nullable=false) private Guardian guardian;
    @Column(nullable=false, length=120) private String senderTag;
    @Column(nullable=false, columnDefinition="TEXT") private String rawContent;
    @Column(length=30) private String signalType;
    @Column(length=20) private String severity;
    @Column(length=100) private String assignedUnit;
    @Column(columnDefinition="TEXT") private String recommendedAction;
    @Column(nullable=false, length=20) private String status;
    @Column(columnDefinition="TEXT") private String personalityNote;
    @Column(nullable=false) private Instant createdAt;
    @Column(nullable=false) private Instant updatedAt;
    @OneToOne(mappedBy="signal", fetch=FetchType.LAZY) private CareResponse careResponse;
    @OneToMany(mappedBy="signal") private List<NotificationLog> notificationLogs = new ArrayList<>();
    public TropelSignal() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Tropel getTropel(){return tropel;} public void setTropel(Tropel v){tropel=v;}
    public Guardian getGuardian(){return guardian;} public void setGuardian(Guardian v){guardian=v;}
    public String getSenderTag(){return senderTag;} public void setSenderTag(String v){senderTag=v;}
    public String getRawContent(){return rawContent;} public void setRawContent(String v){rawContent=v;}
    public String getSignalType(){return signalType;} public void setSignalType(String v){signalType=v;}
    public String getSeverity(){return severity;} public void setSeverity(String v){severity=v;}
    public String getAssignedUnit(){return assignedUnit;} public void setAssignedUnit(String v){assignedUnit=v;}
    public String getRecommendedAction(){return recommendedAction;} public void setRecommendedAction(String v){recommendedAction=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getPersonalityNote(){return personalityNote;} public void setPersonalityNote(String v){personalityNote=v;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
    public Instant getUpdatedAt(){return updatedAt;} public void setUpdatedAt(Instant v){updatedAt=v;}
    public CareResponse getCareResponse(){return careResponse;} public void setCareResponse(CareResponse v){careResponse=v;}
    public List<NotificationLog> getNotificationLogs(){return notificationLogs;}
}
