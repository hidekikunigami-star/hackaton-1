package tuckersoft.bandersnatch.repo;
import tuckersoft.bandersnatch.model.*; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface DecisionRepository extends JpaRepository<Decision,Long>{
 List<Decision> findByPlaythroughOrderByCreatedAtAsc(Playthrough p);
 @Query("select d from Decision d where d.playthrough.user.id=:uid and (:branch is null or d.branchType=:branch) and (:impact is null or d.impactLevel=:impact) and (:status is null or d.status=:status) and (:pid is null or d.playthrough.id=:pid)")
 Page<Decision> search(Long uid,String branch,String impact,String status,Long pid,Pageable pageable);
}
