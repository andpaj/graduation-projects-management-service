package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamMemberDto;

public interface TeamMemberService {

    TeamMemberDto addMemberToTeam(String userId, String teamId);


}
