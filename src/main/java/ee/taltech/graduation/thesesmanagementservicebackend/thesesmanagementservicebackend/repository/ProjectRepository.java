package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findByProjectId(String projectId);


}
