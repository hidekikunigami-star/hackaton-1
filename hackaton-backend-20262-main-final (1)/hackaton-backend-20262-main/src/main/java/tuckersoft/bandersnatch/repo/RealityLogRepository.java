package tuckersoft.bandersnatch.repo; import tuckersoft.bandersnatch.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface RealityLogRepository extends JpaRepository<RealityLog,Long>{List<RealityLog> findByDecisionOrderByCreatedAtAsc(Decision d);}
