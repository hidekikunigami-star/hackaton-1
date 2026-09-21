package com.utec.tropelcare.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="guardians", uniqueConstraints=@UniqueConstraint(name="uk_guardian_email", columnNames="email"))
public class Guardian {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=60) private String displayName;
    @Column(nullable=false, unique=true, length=150) private String email;
    @Column(nullable=false, length=150) private String notificationEmail;
    @Column(nullable=false) private Instant createdAt;
    @OneToMany(mappedBy="guardian") private List<Tropel> tropels = new ArrayList<>();
    @OneToMany(mappedBy="guardian") private List<TropelSignal> signals = new ArrayList<>();
    public Guardian() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getDisplayName(){return displayName;} public void setDisplayName(String v){displayName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getNotificationEmail(){return notificationEmail;} public void setNotificationEmail(String v){notificationEmail=v;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
    public List<Tropel> getTropels(){return tropels;} public List<TropelSignal> getSignals(){return signals;}
}
