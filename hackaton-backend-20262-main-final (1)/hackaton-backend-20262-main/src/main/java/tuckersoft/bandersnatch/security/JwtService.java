package tuckersoft.bandersnatch.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.*; 
@Service public class JwtService {
 private final SecretKey key; private final long exp;
 public JwtService(@Value("${jwt.secret}") String secret,@Value("${jwt.expiration-ms:7200000}") long exp){this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.exp=exp;}
 public String generate(String email){Date now=new Date(); return Jwts.builder().subject(email).issuedAt(now).expiration(new Date(now.getTime()+exp)).signWith(key).compact();}
 public String subject(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
}
