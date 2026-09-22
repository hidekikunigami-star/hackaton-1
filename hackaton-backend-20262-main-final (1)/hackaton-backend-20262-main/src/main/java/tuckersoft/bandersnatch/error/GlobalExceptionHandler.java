package tuckersoft.bandersnatch.error;
import tuckersoft.bandersnatch.dto.DTOs; import jakarta.servlet.http.HttpServletRequest; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import org.springframework.http.converter.HttpMessageNotReadableException; import java.time.Instant;
@RestControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(ApiException.class) ResponseEntity<DTOs.ErrorResponse> api(ApiException e,HttpServletRequest r){return ResponseEntity.status(e.status).body(new DTOs.ErrorResponse(e.error,e.getMessage(),Instant.now(),r.getRequestURI()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<DTOs.ErrorResponse> val(Exception e,HttpServletRequest r){return err(400,"VALIDATION_ERROR","Datos inválidos",r);}
 @ExceptionHandler(HttpMessageNotReadableException.class) ResponseEntity<DTOs.ErrorResponse> read(Exception e,HttpServletRequest r){return err(400,"BAD_REQUEST","Solicitud inválida",r);}
 @ExceptionHandler(Exception.class) ResponseEntity<DTOs.ErrorResponse> other(Exception e,HttpServletRequest r){e.printStackTrace();return err(500,"INTERNAL_ERROR","Error interno",r);}
 private ResponseEntity<DTOs.ErrorResponse> err(int s,String e,String m,HttpServletRequest r){return ResponseEntity.status(s).body(new DTOs.ErrorResponse(e,m,Instant.now(),r.getRequestURI()));}
}
