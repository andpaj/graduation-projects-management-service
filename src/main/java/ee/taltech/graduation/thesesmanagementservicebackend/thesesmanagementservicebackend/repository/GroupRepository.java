package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByGroupClass(String groupClass);
    GroupEntity findByGroupName(String groupName);
    GroupEntity findByGroupId(String groupId);
}
