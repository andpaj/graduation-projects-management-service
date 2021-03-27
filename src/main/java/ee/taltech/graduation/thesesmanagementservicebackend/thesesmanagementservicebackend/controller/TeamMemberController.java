package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.TeamMemberWithTeam;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamMemberService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamMemberDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teamMember")
public class TeamMemberController {

    @Autowired
    TeamMemberService teamMemberService;


    @GetMapping(path = "/{id}")
    private TeamMemberWithTeam getTeamMemberById(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();
        TeamMemberDto teamMemberDto = teamMemberService.getTeamMemberById(id);

        TeamMemberWithTeam teamMemberRest = modelMapper.map(teamMemberDto, TeamMemberWithTeam.class);

        return teamMemberRest;

    }


    @PostMapping(path = "/addTeamMember")
    private TeamMemberWithTeam addMemberToTeam(@RequestParam String userId, @RequestParam String teamId){

        TeamMemberDto savedTeamMember = teamMemberService.addMemberToTeam(userId, teamId);
        ModelMapper modelMapper = new ModelMapper();
        TeamMemberWithTeam teamMemberRest = modelMapper.map(savedTeamMember, TeamMemberWithTeam.class);

        return teamMemberRest;

    }

    @PostMapping(path = "/accept")
    private String acceptTeamMembership(@RequestParam String userId, @RequestParam String teamMemberId){

        TeamMemberDto teamMemberDto = teamMemberService.acceptMembership(userId, teamMemberId);

        return teamMemberDto.getUser().getFirstName() + " are now part of the team " + teamMemberDto.getTeam().getTeamName();

    }

    @PostMapping(path = "/decline")
    private void declineTeamMembership(@RequestParam String userId, @RequestParam String teamMemberId){

        teamMemberService.declineMembership(userId, teamMemberId);
    }

}
