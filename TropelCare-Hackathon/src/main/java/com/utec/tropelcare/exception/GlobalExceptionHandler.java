package com.utec.tropelcare.exception;
import jakarta.servlet.http.HttpServletRequest; import jakarta.validation.ConstraintViolationException; import org.springframework.dao.DataIntegrityViolationException; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant;
@RestControllerAdvice public class GlobalExceptionHandler {
 private ResponseEntity<ErrorResponse> r(HttpStatus s,String type,String m,HttpServletRequest q){return ResponseEntity.status(s).body(new ErrorResponse(type,m,Instant.now(),q.getRequestURI()));}
 @ExceptionHandler(NotFoundException.class) ResponseEntity<ErrorResponse> nf(NotFoundException e,HttpServletRequest q){return r(HttpStatus.NOT_FOUND,"NOT_FOUND",e.getMessage(),q);}
 @ExceptionHandler({BusinessException.class,MethodArgumentNotValidException.class,ConstraintViolationException.class}) ResponseEntity<ErrorResponse> bad(Exception e,HttpServletRequest q){String m=e instanceof MethodArgumentNotValidException v?v.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).findFirst().orElse("Validación fallida"):e.getMessage();return r(HttpStatus.BAD_REQUEST,"BAD_REQUEST",m,q);}
 @ExceptionHandler(ConflictException.class) ResponseEntity<ErrorResponse> con(ConflictException e,HttpServletRequest q){return r(HttpStatus.CONFLICT,"CONFLICT",e.getMessage(),q);}
 @ExceptionHandler(DataIntegrityViolationException.class) ResponseEntity<ErrorResponse> di(DataIntegrityViolationException e,HttpServletRequest q){return r(HttpStatus.CONFLICT,"CONFLICT","El recurso ya existe o viola una restricción de integridad",q);}
 @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> other(Exception e,HttpServletRequest q){return r(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_ERROR","Ocurrió un error interno",q);}
}
