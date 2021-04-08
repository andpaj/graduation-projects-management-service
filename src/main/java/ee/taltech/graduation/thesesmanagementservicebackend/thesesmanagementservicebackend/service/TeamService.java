package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;

import java.util.List;

public interface TeamService {

    TeamDto createTeam(String userId, TeamDto teamDto, List<String> members);
    TeamDto getTeamById(String teamId);
    List<TeamDto> getTeamsByUserId(String userId);
    void deleteTeam(String teamId);
    TeamDto finishTeamProject(String teamId);
}
