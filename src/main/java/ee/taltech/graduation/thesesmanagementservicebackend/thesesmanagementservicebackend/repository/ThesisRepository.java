package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ThesisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisRepository extends JpaRepository<ThesisEntity, Long> {

    ThesisEntity findByThesisId(String thesisId);


}
