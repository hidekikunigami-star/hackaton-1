package tuckersoft.bandersnatch.model;
import jakarta.persistence.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @Column(nullable=false,unique=true) String email;
 @Column(nullable=false) String password;
 @Column(nullable=false) String displayName;
 @Column(nullable=false) String role;
 @Column(nullable=false) Instant createdAt;
 @OneToMany(mappedBy="user") List<Playthrough> playthroughs=new ArrayList<>();
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getEmail(){return email;} public String getPassword(){return password;}
 public String getDisplayName(){return displayName;} public String getRole(){return role;} public Instant getCreatedAt(){return createdAt;}
 public void setEmail(String v){email=v;} public void setPassword(String v){password=v;} public void setDisplayName(String v){displayName=v;}
 public void setRole(String v){role=v;} public void setCreatedAt(Instant v){createdAt=v;}
}
