package com.utec.tropelcare.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tropels", uniqueConstraints=@UniqueConstraint(name="uk_tropel_name", columnNames="name"))
public class Tropel {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=40) private String name;
    @Column(nullable=false, length=30) private String species;
    @Column(nullable=false, length=20) private String vitalState;
    @Column(nullable=false) private Integer energyLevel;
    @Column(nullable=false) private Integer chaosIndex;
    @Column(nullable=false) private Integer mutationStage;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="sector_id", nullable=false) private Sector sector;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="guardian_id", nullable=false) private Guardian guardian;
    @Column(nullable=false) private Instant createdAt;
    @Column(nullable=false) private Instant updatedAt;
    @OneToMany(mappedBy="tropel") private List<TropelSignal> signals = new ArrayList<>();
    public Tropel() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getSpecies(){return species;} public void setSpecies(String v){species=v;}
    public String getVitalState(){return vitalState;} public void setVitalState(String v){vitalState=v;}
    public Integer getEnergyLevel(){return energyLevel;} public void setEnergyLevel(Integer v){energyLevel=v;}
    public Integer getChaosIndex(){return chaosIndex;} public void setChaosIndex(Integer v){chaosIndex=v;}
    public Integer getMutationStage(){return mutationStage;} public void setMutationStage(Integer v){mutationStage=v;}
    public Sector getSector(){return sector;} public void setSector(Sector v){sector=v;}
    public Guardian getGuardian(){return guardian;} public void setGuardian(Guardian v){guardian=v;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
    public Instant getUpdatedAt(){return updatedAt;} public void setUpdatedAt(Instant v){updatedAt=v;}
    public List<TropelSignal> getSignals(){return signals;}
}
