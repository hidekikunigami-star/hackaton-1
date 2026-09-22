package tuckersoft.bandersnatch.service;
import tuckersoft.bandersnatch.dto.DTOs; import tuckersoft.bandersnatch.error.ApiException; import tuckersoft.bandersnatch.model.User; import tuckersoft.bandersnatch.repo.UserRepository; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Service; import java.util.*;
@Service public class UserService {
 private final UserRepository repo; public UserService(UserRepository r){repo=r;}
 public User current(){String email=SecurityContextHolder.getContext().getAuthentication().getName();return repo.findByEmail(email).orElseThrow(()->new ApiException(401,"UNAUTHORIZED","Usuario no encontrado"));}
 public DTOs.UserResponse dto(User u){return new DTOs.UserResponse(u.getId(),u.getEmail(),u.getDisplayName(),u.getRole(),u.getCreatedAt());}
 public DTOs.UserResponse me(){return dto(current());}
 public List<DTOs.UserResponse> all(){return repo.findAll().stream().map(this::dto).toList();}
 public DTOs.UserResponse role(long id,String role){if(!role.equals("ROLE_USER")&&!role.equals("ROLE_ADMIN"))throw new ApiException(400,"INVALID_ROLE","Rol inválido");User me=current();if(me.getId().equals(id))throw new ApiException(400,"SELF_ROLE_CHANGE","No puedes cambiar tu propio rol");User u=repo.findById(id).orElseThrow(()->new ApiException(404,"USER_NOT_FOUND","Usuario no encontrado"));u.setRole(role);return dto(repo.save(u));}
}
