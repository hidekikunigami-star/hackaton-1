package tuckersoft.bandersnatch.dto;
import jakarta.validation.constraints.*; import java.time.Instant; import java.util.*;
public final class DTOs {
 public record RegisterRequest(@NotBlank @Email String email,@NotBlank @Size(min=6) String password,@NotBlank @Size(min=3,max=60) String displayName){}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){}
 public record AuthResponse(String token,String type,String email,String displayName,String role){}
 public record UserResponse(Long id,String email,String displayName,String role,Instant createdAt){}
 public record RoleRequest(@NotBlank String role){}
 public record NodeRequest(@NotBlank @Size(min=3,max=40) String nodeCode,@NotBlank @Size(min=3,max=80) String title,@NotBlank @Size(min=10) String sceneText,@NotNull @Min(1) Integer branchCapacity,String primaryBranchCode,String glitchBranchCode){}
 public record NodeResponse(Long id,String nodeCode,String title,String sceneText,Integer branchCapacity,Integer currentBranches,String primaryBranchCode,String glitchBranchCode,Instant createdAt){}
 public record PlaythroughRequest(@NotBlank @Size(min=2,max=40) String playerTag,@NotBlank String startNodeCode){}
 public record PlaythroughResponse(Long id,String playerTag,String ownerEmail,String startNodeCode,String currentNodeCode,Integer lucidity,Integer controlLevel,String status,String endingCode,Instant createdAt,Instant updatedAt){}
 public record DecisionRequest(@NotNull Long playthroughId,@NotBlank @Size(min=10) String rawInput,@NotBlank String impactLevel){}
 public record DecisionResponse(Long id,Long playthroughId,String playerTag,String sourceNodeCode,String resolvedNodeCode,String rawInput,String branchType,String impactLevel,String handlerUnit,String outcomeCode,String status,String playthroughStatus,Integer lucidity,Integer controlLevel,String endingCode,Instant createdAt,Instant updatedAt){}
 public record PageResponse<T>(List<T> content,long totalElements,int totalPages,int currentPage,int size){}
 public record PathStep(int order,Long decisionId,String fromNodeCode,String toNodeCode,String branchType,String impactLevel,Instant createdAt){}
 public record PathResponse(Long playthroughId,String playerTag,String status,String endingCode,String startNodeCode,String currentNodeCode,List<PathStep> steps){}
 public record RealityLogResponse(Long id,Long decisionId,String recipientEmail,String subject,String logStatus,String errorMessage,Instant sentAt,Instant createdAt){}
 public record ErrorResponse(String error,String message,Instant timestamp,String path){}
}
