package tuckersoft.bandersnatch.repo; import tuckersoft.bandersnatch.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PlaythroughRepository extends JpaRepository<Playthrough,Long>{boolean existsByPlayerTag(String tag); List<Playthrough> findByUserOrderByCreatedAtDesc(User u); List<Playthrough> findAllByOrderByCreatedAtDesc();}
