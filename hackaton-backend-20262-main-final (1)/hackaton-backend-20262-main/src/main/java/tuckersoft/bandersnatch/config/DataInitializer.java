package tuckersoft.bandersnatch.config;
import tuckersoft.bandersnatch.model.User; import tuckersoft.bandersnatch.repo.UserRepository; import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.Instant;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner init(UserRepository repo,PasswordEncoder enc,@Value("${app.admin.display-name}") String name,@Value("${app.admin.email}") String email,@Value("${app.admin.password}") String pass){return args->{if(repo.findByEmail(email).isEmpty()){User u=new User();u.setEmail(email);u.setDisplayName(name);u.setPassword(enc.encode(pass));u.setRole("ROLE_ADMIN");u.setCreatedAt(Instant.now());repo.save(u);}};}
}
