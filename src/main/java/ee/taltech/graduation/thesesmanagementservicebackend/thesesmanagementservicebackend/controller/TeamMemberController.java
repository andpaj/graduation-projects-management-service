package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamMemberRest.TeamMemberWithTeam;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamMemberService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamMemberDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teamMember")
public class TeamMemberController {

    @Autowired
    TeamMemberService teamMemberService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{teamMemberId}")
    public TeamMemberWithTeam getTeamMemberById(@PathVariable String teamMemberId){

        ModelMapper modelMapper = new ModelMapper();
        TeamMemberDto teamMemberDto = teamMemberService.getTeamMemberById(teamMemberId);

        TeamMemberWithTeam teamMemberRest = modelMapper.map(teamMemberDto, TeamMemberWithTeam.class);

        return teamMemberRest;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    @PostMapping(path = "/addTeamMember")
    public TeamMemberWithTeam addMemberToTeam(@RequestParam String userId, @RequestParam String teamId){

        TeamMemberDto savedTeamMember = teamMemberService.addMemberToTeam(userId, teamId);
        ModelMapper modelMapper = new ModelMapper();
        TeamMemberWithTeam teamMemberRest = modelMapper.map(savedTeamMember, TeamMemberWithTeam.class);

        return teamMemberRest;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')or #userId == principal.userId")
    @PostMapping(path = "/accept")
    public TeamMemberWithTeam acceptTeamMembership(@RequestParam String userId, @RequestParam String teamMemberId){

        TeamMemberDto teamMemberDto = teamMemberService.acceptMembership(userId, teamMemberId);

        ModelMapper modelMapper = new ModelMapper();
        TeamMemberWithTeam teamMemberRest = modelMapper.map(teamMemberDto, TeamMemberWithTeam.class);

        return teamMemberRest;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')or #userId == principal.userId")
    @PostMapping(path = "/decline")
    public String declineTeamMembership(@RequestParam String userId, @RequestParam String teamMemberId){

        teamMemberService.declineMembership(userId, teamMemberId);

        return teamMemberId;
    }

}
