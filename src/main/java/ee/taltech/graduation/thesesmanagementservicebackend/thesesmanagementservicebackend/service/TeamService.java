package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;

import java.util.List;

public interface TeamService {

    TeamDto createTeam(String userId, TeamDto teamDto);
    TeamDto getTeamById(String id);
    List<TeamDto> getTeamsByUserId(String id);
}
