package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.TeamDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamRest.TeamRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamRest.TeamRestWithoutMembers;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{teamId}")
    private TeamRest getTeamById(@PathVariable String teamId){

        ModelMapper modelMapper = new ModelMapper();

        TeamDto team = teamService.getTeamById(teamId);
        TeamRest teamRest = modelMapper.map(team, TeamRest.class);

        return teamRest;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PostMapping(path = "/create/{userId}")
    private TeamRest createTeam(@PathVariable String userId, @RequestBody TeamDetailsRequestModel teamDetails){
        ModelMapper modelMapper = new ModelMapper();

        List<String> members = teamDetails.getUsers();

        TeamDto teamDto = modelMapper.map(teamDetails, TeamDto.class);

        TeamDto savedTeamDto = teamService.createTeam(userId, teamDto, members);

        TeamRest teamRest = modelMapper.map(savedTeamDto, TeamRest.class);
        return teamRest;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/teamsByUserId/{userId}")
    private List<TeamRestWithoutMembers> getTeamsByUserId(@PathVariable String userId){
        ModelMapper modelMapper = new ModelMapper();

        List<TeamDto> teams = teamService.getTeamsByUserId(userId);

        List<TeamRestWithoutMembers> teamsRest = new ArrayList<>();

        for (TeamDto team: teams){
            TeamRestWithoutMembers teamWithoutMember = modelMapper.map(team, TeamRestWithoutMembers.class);
            teamsRest.add(teamWithoutMember);

        }

        return teamsRest;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PostMapping(path = "/finishTeamProject/{teamId}")
    private TeamRest finishTeamProject(@PathVariable String teamId){
        ModelMapper modelMapper = new ModelMapper();

        TeamDto teamDto = teamService.finishTeamProject(teamId);

        TeamRest teamRest = modelMapper.map(teamDto, TeamRest.class);

        return teamRest;

    }


    @DeleteMapping(path = "/delete/{teamId}")
    private String deleteTeam(@PathVariable String teamId){

        teamService.deleteTeam(teamId);

        return teamId;



    }



}
