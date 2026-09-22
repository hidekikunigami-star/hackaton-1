package tuckersoft.bandersnatch.security;
import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; import org.springframework.stereotype.Component; import java.io.IOException;
@Component public class JwtFilter extends org.springframework.web.filter.OncePerRequestFilter {
 private final JwtService jwt; private final CustomUserDetailsService uds; public JwtFilter(JwtService j,CustomUserDetailsService u){jwt=j;uds=u;}
 protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization");
  if(h!=null && h.startsWith("Bearer ")){try{String email=jwt.subject(h.substring(7)); UserDetails ud=uds.loadUserByUsername(email); var a=new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities()); a.setDetails(new WebAuthenticationDetailsSource().buildDetails(req)); SecurityContextHolder.getContext().setAuthentication(a);}catch(Exception ignored){SecurityContextHolder.clearContext();}}
  chain.doFilter(req,res);
 }
}
