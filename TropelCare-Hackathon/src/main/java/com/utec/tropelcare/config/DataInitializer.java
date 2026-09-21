package com.utec.tropelcare.config;
import com.utec.tropelcare.entity.Guardian; import com.utec.tropelcare.repository.GuardianRepository; import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.CommandLineRunner; import org.springframework.stereotype.Component; import java.time.Instant;
@Component public class DataInitializer implements CommandLineRunner{
 private final GuardianRepository repo; @Value("${app.admin.display-name}") String name; @Value("${app.admin.email}") String email; @Value("${app.admin.notification-email}") String notificationEmail;
 public DataInitializer(GuardianRepository repo){this.repo=repo;}
 public void run(String... args){if(repo.findByEmail(email).isEmpty()){Guardian g=new Guardian();g.setDisplayName(name);g.setEmail(email);g.setNotificationEmail(notificationEmail);g.setCreatedAt(Instant.now());repo.save(g);}}
}
