package tuckersoft.bandersnatch.model;
import jakarta.persistence.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="playthroughs",uniqueConstraints=@UniqueConstraint(columnNames="playerTag"))
public class Playthrough {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @Column(nullable=false,unique=true,length=40) String playerTag;
 @ManyToOne(optional=false) User user;
 @Column(nullable=false) String startNodeCode;
 @ManyToOne(optional=false) StoryNode currentNode;
 @Column(nullable=false) Integer lucidity=100, controlLevel=0;
 @Column(nullable=false) String status="ACTIVA"; String endingCode;
 @Column(nullable=false) Instant createdAt,updatedAt;
 @OneToMany(mappedBy="playthrough") List<Decision> decisions=new ArrayList<>();
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getPlayerTag(){return playerTag;} public User getUser(){return user;} public String getStartNodeCode(){return startNodeCode;} public StoryNode getCurrentNode(){return currentNode;} public Integer getLucidity(){return lucidity;} public Integer getControlLevel(){return controlLevel;} public String getStatus(){return status;} public String getEndingCode(){return endingCode;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
 public void setPlayerTag(String v){playerTag=v;} public void setUser(User v){user=v;} public void setStartNodeCode(String v){startNodeCode=v;} public void setCurrentNode(StoryNode v){currentNode=v;} public void setLucidity(Integer v){lucidity=v;} public void setControlLevel(Integer v){controlLevel=v;} public void setStatus(String v){status=v;} public void setEndingCode(String v){endingCode=v;} public void setCreatedAt(Instant v){createdAt=v;} public void setUpdatedAt(Instant v){updatedAt=v;}
}
