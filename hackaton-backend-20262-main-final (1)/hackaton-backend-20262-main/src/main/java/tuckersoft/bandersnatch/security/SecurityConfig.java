package tuckersoft.bandersnatch.security;
import com.fasterxml.jackson.databind.ObjectMapper; import jakarta.servlet.http.*; import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.*; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; import tuckersoft.bandersnatch.dto.DTOs; import java.time.Instant;
@Configuration @EnableMethodSecurity public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain chain(HttpSecurity http,JwtFilter filter)throws Exception{
  http.csrf(c->c.disable()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .exceptionHandling(e->e.authenticationEntryPoint((req,res,ex)->write(res,401,"UNAUTHORIZED","Autenticación requerida",req.getRequestURI()))
   .accessDeniedHandler((req,res,ex)->write(res,403,"FORBIDDEN","No tienes permisos para este recurso",req.getRequestURI())))
   .authorizeHttpRequests(a->a.requestMatchers("/api/v1/auth/**").permitAll().requestMatchers("/api/v1/nodes").hasRole("ADMIN").requestMatchers("/api/v1/users","/api/v1/users/*/role").hasRole("ADMIN").anyRequest().authenticated())
   .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }
 static void write(HttpServletResponse res,int status,String err,String msg,String path)throws java.io.IOException{res.setStatus(status);res.setContentType("application/json");new ObjectMapper().writeValue(res.getOutputStream(),new DTOs.ErrorResponse(err,msg,Instant.now(),path));}
}
