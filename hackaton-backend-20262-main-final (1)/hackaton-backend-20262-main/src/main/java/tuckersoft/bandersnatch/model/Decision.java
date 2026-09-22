package tuckersoft.bandersnatch.model;
import jakarta.persistence.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="decisions")
public class Decision {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @ManyToOne(optional=false) Playthrough playthrough;
 @ManyToOne(optional=false) StoryNode node;
 @Column(nullable=false,columnDefinition="TEXT") String rawInput;
 @Column(nullable=false) String branchType,impactLevel,handlerUnit,outcomeCode;
 String resolvedNodeCode; @Column(nullable=false) String status;
 @Column(nullable=false) Instant createdAt,updatedAt;
 @OneToMany(mappedBy="decision") List<RealityLog> realityLogs=new ArrayList<>();
 public Long getId(){return id;} public Playthrough getPlaythrough(){return playthrough;} public StoryNode getNode(){return node;} public String getRawInput(){return rawInput;} public String getBranchType(){return branchType;} public String getImpactLevel(){return impactLevel;} public String getHandlerUnit(){return handlerUnit;} public String getOutcomeCode(){return outcomeCode;} public String getResolvedNodeCode(){return resolvedNodeCode;} public String getStatus(){return status;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
 public void setPlaythrough(Playthrough v){playthrough=v;} public void setNode(StoryNode v){node=v;} public void setRawInput(String v){rawInput=v;} public void setBranchType(String v){branchType=v;} public void setImpactLevel(String v){impactLevel=v;} public void setHandlerUnit(String v){handlerUnit=v;} public void setOutcomeCode(String v){outcomeCode=v;} public void setResolvedNodeCode(String v){resolvedNodeCode=v;} public void setStatus(String v){status=v;} public void setCreatedAt(Instant v){createdAt=v;} public void setUpdatedAt(Instant v){updatedAt=v;}
}
