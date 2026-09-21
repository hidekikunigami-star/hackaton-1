package com.utec.tropelcare.repository;
import com.utec.tropelcare.entity.NotificationLog; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface NotificationLogRepository extends JpaRepository<NotificationLog,Long>{ List<NotificationLog> findBySignalIdOrderByCreatedAtDesc(Long signalId); }
