package tuckersoft.bandersnatch.security;
import tuckersoft.bandersnatch.repo.UserRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository repo; public CustomUserDetailsService(UserRepository r){repo=r;}
 public UserDetails loadUserByUsername(String email){var u=repo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Credenciales inválidas")); return User.withUsername(u.getEmail()).password(u.getPassword()).authorities(u.getRole()).build();}
}
