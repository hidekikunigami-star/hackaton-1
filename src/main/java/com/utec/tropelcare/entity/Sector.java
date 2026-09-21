package com.utec.tropelcare.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sectors", uniqueConstraints=@UniqueConstraint(name="uk_sector_code", columnNames="sectorCode"))
public class Sector {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=80) private String sectorCode;
    @Column(nullable=false, length=40) private String climate;
    @Column(nullable=false) private Integer capacity;
    @Column(nullable=false) private Integer currentLoad=0;
    @Column(nullable=false) private Integer stabilityLevel=100;
    @Column(nullable=false) private Instant createdAt;
    @OneToMany(mappedBy="sector") private List<Tropel> tropels = new ArrayList<>();
    public Sector() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getSectorCode(){return sectorCode;} public void setSectorCode(String v){sectorCode=v;}
    public String getClimate(){return climate;} public void setClimate(String v){climate=v;}
    public Integer getCapacity(){return capacity;} public void setCapacity(Integer v){capacity=v;}
    public Integer getCurrentLoad(){return currentLoad;} public void setCurrentLoad(Integer v){currentLoad=v;}
    public Integer getStabilityLevel(){return stabilityLevel;} public void setStabilityLevel(Integer v){stabilityLevel=v;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
    public List<Tropel> getTropels(){return tropels;}
}
