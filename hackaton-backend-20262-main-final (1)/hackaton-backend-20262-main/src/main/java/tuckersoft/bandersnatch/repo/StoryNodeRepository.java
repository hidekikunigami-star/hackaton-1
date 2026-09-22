package tuckersoft.bandersnatch.repo; import tuckersoft.bandersnatch.model.StoryNode; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface StoryNodeRepository extends JpaRepository<StoryNode,Long>{Optional<StoryNode> findByNodeCode(String code); boolean existsByNodeCode(String code);}
