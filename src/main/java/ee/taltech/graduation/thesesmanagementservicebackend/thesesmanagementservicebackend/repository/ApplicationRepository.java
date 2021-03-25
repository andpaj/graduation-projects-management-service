package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
}
