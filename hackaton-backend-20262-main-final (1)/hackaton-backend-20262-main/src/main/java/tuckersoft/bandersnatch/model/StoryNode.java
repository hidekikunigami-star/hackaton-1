package tuckersoft.bandersnatch.model;
import jakarta.persistence.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="story_nodes",uniqueConstraints=@UniqueConstraint(columnNames="nodeCode"))
public class StoryNode {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @Column(nullable=false,unique=true,length=40) String nodeCode;
 @Column(nullable=false,length=80) String title;
 @Column(nullable=false,columnDefinition="TEXT") String sceneText;
 @Column(nullable=false) Integer branchCapacity;
 @Column(nullable=false) Integer currentBranches=0;
 String primaryBranchCode; String glitchBranchCode; @Column(nullable=false) Instant createdAt;
 @OneToMany(mappedBy="currentNode") List<Playthrough> playthroughs=new ArrayList<>();
 @OneToMany(mappedBy="node") List<Decision> decisions=new ArrayList<>();
 public Long getId(){return id;} public String getNodeCode(){return nodeCode;} public String getTitle(){return title;} public String getSceneText(){return sceneText;}
 public Integer getBranchCapacity(){return branchCapacity;} public Integer getCurrentBranches(){return currentBranches;} public String getPrimaryBranchCode(){return primaryBranchCode;} public String getGlitchBranchCode(){return glitchBranchCode;} public Instant getCreatedAt(){return createdAt;}
 public void setNodeCode(String v){nodeCode=v;} public void setTitle(String v){title=v;} public void setSceneText(String v){sceneText=v;} public void setBranchCapacity(Integer v){branchCapacity=v;} public void setCurrentBranches(Integer v){currentBranches=v;} public void setPrimaryBranchCode(String v){primaryBranchCode=v;} public void setGlitchBranchCode(String v){glitchBranchCode=v;} public void setCreatedAt(Instant v){createdAt=v;}
}
