package tuckersoft.bandersnatch.service;
import tuckersoft.bandersnatch.dto.DTOs; import tuckersoft.bandersnatch.error.ApiException; import tuckersoft.bandersnatch.model.StoryNode; import tuckersoft.bandersnatch.repo.StoryNodeRepository; import org.springframework.stereotype.Service; import java.time.Instant;
@Service public class NodeService {
 private final StoryNodeRepository repo; public NodeService(StoryNodeRepository r){repo=r;}
 public DTOs.NodeResponse create(DTOs.NodeRequest r){if(repo.existsByNodeCode(r.nodeCode()))throw new ApiException(409,"NODE_EXISTS","El nodeCode ya existe");StoryNode n=new StoryNode();n.setNodeCode(r.nodeCode());n.setTitle(r.title());n.setSceneText(r.sceneText());n.setBranchCapacity(r.branchCapacity());n.setCurrentBranches(0);n.setPrimaryBranchCode(r.primaryBranchCode());n.setGlitchBranchCode(r.glitchBranchCode());n.setCreatedAt(Instant.now());return dto(repo.save(n));}
 public DTOs.NodeResponse get(long id){return dto(repo.findById(id).orElseThrow(()->new ApiException(404,"NODE_NOT_FOUND","Nodo no encontrado")));}
 public java.util.List<DTOs.NodeResponse> all(){return repo.findAll().stream().map(this::dto).toList();}
 public DTOs.NodeResponse dto(StoryNode n){return new DTOs.NodeResponse(n.getId(),n.getNodeCode(),n.getTitle(),n.getSceneText(),n.getBranchCapacity(),n.getCurrentBranches(),n.getPrimaryBranchCode(),n.getGlitchBranchCode(),n.getCreatedAt());}
}
