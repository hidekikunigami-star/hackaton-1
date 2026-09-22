package tuckersoft.bandersnatch.service;
import tuckersoft.bandersnatch.dto.DTOs; import tuckersoft.bandersnatch.error.ApiException; import tuckersoft.bandersnatch.model.User; import tuckersoft.bandersnatch.repo.UserRepository; import tuckersoft.bandersnatch.security.JwtService; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import java.time.Instant;
@Service public class AuthService {
 private final UserRepository users; private final PasswordEncoder enc; private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,JwtService j){users=u;enc=e;jwt=j;}
 public DTOs.AuthResponse register(DTOs.RegisterRequest r){if(users.existsByEmail(r.email()))throw new ApiException(409,"EMAIL_EXISTS","El email ya está registrado"); User u=new User();u.setEmail(r.email());u.setPassword(enc.encode(r.password()));u.setDisplayName(r.displayName());u.setRole("ROLE_USER");u.setCreatedAt(Instant.now());users.save(u);return auth(u);}
 public DTOs.AuthResponse login(DTOs.LoginRequest r){User u=users.findByEmail(r.email()).orElseThrow(()->new ApiException(401,"UNAUTHORIZED","Credenciales inválidas"));if(!enc.matches(r.password(),u.getPassword()))throw new ApiException(401,"UNAUTHORIZED","Credenciales inválidas");return auth(u);}
 private DTOs.AuthResponse auth(User u){return new DTOs.AuthResponse(jwt.generate(u.getEmail()),"Bearer",u.getEmail(),u.getDisplayName(),u.getRole());}
}
