package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.TeamMemberRest;
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


    @PostMapping(path = "/addTeamMember")
    private TeamMemberRest addMemberToTeam(@RequestParam String userId, @RequestParam String teamId){

        TeamMemberDto savedTeamMember = teamMemberService.addMemberToTeam(userId, teamId);
        ModelMapper modelMapper = new ModelMapper();
        TeamMemberRest teamMemberRest = modelMapper.map(savedTeamMember, TeamMemberRest.class);

        return teamMemberRest;

    }





}
