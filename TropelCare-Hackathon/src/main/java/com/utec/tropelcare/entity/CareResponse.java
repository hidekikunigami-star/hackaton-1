package com.utec.tropelcare.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="care_responses", uniqueConstraints=@UniqueConstraint(name="uk_care_signal", columnNames="signal_id"))
public class CareResponse {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="signal_id", nullable=false, unique=true) private TropelSignal signal;
    @Column(nullable=false, length=60) private String responseCode;
    @Column(nullable=false, columnDefinition="TEXT") private String description;
    @Column(nullable=false) private Instant createdAt;
    public CareResponse() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public TropelSignal getSignal(){return signal;} public void setSignal(TropelSignal v){signal=v;}
    public String getResponseCode(){return responseCode;} public void setResponseCode(String v){responseCode=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
}
