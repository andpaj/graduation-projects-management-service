package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.TeamDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.TeamMemberRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.TeamRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.TeamRestWithoutMembers;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamMemberDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping(path = "/{id}")
    private TeamRest getTeamById(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();

        TeamDto team = teamService.getTeamById(id);
        TeamRest teamRest = modelMapper.map(team, TeamRest.class);

        return teamRest;


    }


    @PostMapping(path = "/create/{id}")
    private TeamRest createTeam(@PathVariable String id, @RequestBody TeamDetailsRequestModel teamDetails){
        ModelMapper modelMapper = new ModelMapper();

        List<String> members = teamDetails.getUsers();

        TeamDto teamDto = modelMapper.map(teamDetails, TeamDto.class);

        TeamDto savedTeamDto = teamService.createTeam(id, teamDto, members);

        TeamRest teamRest = modelMapper.map(savedTeamDto, TeamRest.class);
        return teamRest;
    }


    @GetMapping(path = "/teamsByUserId/{id}")
    private List<TeamRestWithoutMembers> getTeamsByUserId(@PathVariable String id){
        ModelMapper modelMapper = new ModelMapper();

        List<TeamDto> teams = teamService.getTeamsByUserId(id);

        List<TeamRestWithoutMembers> teamsRest = new ArrayList<>();

        for (TeamDto team: teams){
            TeamRestWithoutMembers teamWithoutMember = modelMapper.map(team, TeamRestWithoutMembers.class);
            teamsRest.add(teamWithoutMember);

        }

        return teamsRest;

    }



}