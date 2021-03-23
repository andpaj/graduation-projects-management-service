package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Long> {

    TeamMemberEntity findByTeamMemberId(String id);

}
