package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ProjectEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findByProjectId(String projectId);
    List<ProjectEntity> findByUser(UserEntity userEntity);


}
