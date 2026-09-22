package tuckersoft.bandersnatch.model;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="reality_logs")
public class RealityLog {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @ManyToOne(optional=false) Decision decision;
 @Column(nullable=false) String recipientEmail; @Column(nullable=false) String subject; @Column(nullable=false) String logStatus;
 @Column(columnDefinition="TEXT") String errorMessage; Instant sentAt; @Column(nullable=false) Instant createdAt;
 public Long getId(){return id;} public Decision getDecision(){return decision;} public String getRecipientEmail(){return recipientEmail;} public String getSubject(){return subject;} public String getLogStatus(){return logStatus;} public String getErrorMessage(){return errorMessage;} public Instant getSentAt(){return sentAt;} public Instant getCreatedAt(){return createdAt;}
 public void setDecision(Decision v){decision=v;} public void setRecipientEmail(String v){recipientEmail=v;} public void setSubject(String v){subject=v;} public void setLogStatus(String v){logStatus=v;} public void setErrorMessage(String v){errorMessage=v;} public void setSentAt(Instant v){sentAt=v;} public void setCreatedAt(Instant v){createdAt=v;}
}
