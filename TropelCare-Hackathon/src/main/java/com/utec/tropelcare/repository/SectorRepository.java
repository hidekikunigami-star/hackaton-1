package com.utec.tropelcare.repository;
import com.utec.tropelcare.entity.Sector; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface SectorRepository extends JpaRepository<Sector,Long>{ Optional<Sector> findBySectorCode(String code); }
