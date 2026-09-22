package tuckersoft.bandersnatch.controller;
import tuckersoft.bandersnatch.dto.DTOs; import tuckersoft.bandersnatch.repo.RealityLogRepository; import tuckersoft.bandersnatch.service.DecisionService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/v1/decisions") public class DecisionController{
 private final DecisionService s;private final RealityLogRepository logs;public DecisionController(DecisionService s,RealityLogRepository l){this.s=s;this.logs=l;}
 @PostMapping ResponseEntity<DTOs.DecisionResponse> create(@Valid @RequestBody DTOs.DecisionRequest r,@RequestHeader(value="X-Bandersnatch-Simulate",required=false) String sim){return ResponseEntity.status(201).body(s.decide(r,"MAIL_FAILURE".equals(sim)));}
 @GetMapping DTOs.PageResponse<DTOs.DecisionResponse> all(@RequestParam(required=false) String branchType,@RequestParam(required=false) String impactLevel,@RequestParam(required=false) String status,@RequestParam(required=false) Long playthroughId,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){var p=s.searchRaw(branchType,impactLevel,status,playthroughId,page,size);return new DTOs.PageResponse<>(p.getContent().stream().map(s::dto).toList(),p.getTotalElements(),p.getTotalPages(),page,size);}
 @GetMapping("/{id}") DTOs.DecisionResponse get(@PathVariable long id){return s.get(id);}
 @GetMapping("/{id}/reality-logs") List<DTOs.RealityLogResponse> logs(@PathVariable long id){return s.logs(id,logs);}
}
