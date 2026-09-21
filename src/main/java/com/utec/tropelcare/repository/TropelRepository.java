package com.utec.tropelcare.repository;
import com.utec.tropelcare.entity.Tropel; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.data.jpa.repository.JpaSpecificationExecutor; import java.util.Optional;
public interface TropelRepository extends JpaRepository<Tropel,Long>, JpaSpecificationExecutor<Tropel>{ Optional<Tropel> findByName(String name); }
