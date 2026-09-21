package com.utec.tropelcare.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name="notification_logs")
public class NotificationLog {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="signal_id", nullable=false) private TropelSignal signal;
    @Column(nullable=false, length=150) private String recipientEmail;
    @Column(nullable=false, length=255) private String subject;
    @Column(nullable=false, length=20) private String notifStatus;
    @Column(columnDefinition="TEXT") private String errorMessage;
    private Instant sentAt;
    @Column(nullable=false) private Instant createdAt;
    public NotificationLog() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public TropelSignal getSignal(){return signal;} public void setSignal(TropelSignal v){signal=v;}
    public String getRecipientEmail(){return recipientEmail;} public void setRecipientEmail(String v){recipientEmail=v;}
    public String getSubject(){return subject;} public void setSubject(String v){subject=v;}
    public String getNotifStatus(){return notifStatus;} public void setNotifStatus(String v){notifStatus=v;}
    public String getErrorMessage(){return errorMessage;} public void setErrorMessage(String v){errorMessage=v;}
    public Instant getSentAt(){return sentAt;} public void setSentAt(Instant v){sentAt=v;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
}
