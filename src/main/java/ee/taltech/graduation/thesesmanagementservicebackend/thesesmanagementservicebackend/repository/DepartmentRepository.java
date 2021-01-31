package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    DepartmentEntity findByDepartmentId(String departmentId);


}
