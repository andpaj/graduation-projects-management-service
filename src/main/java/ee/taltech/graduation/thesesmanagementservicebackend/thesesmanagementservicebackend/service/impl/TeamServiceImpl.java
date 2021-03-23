package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamMemberEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamMemberRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public TeamDto createTeam(String userId, TeamDto teamDto) {

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new ServiceException("User with Id " + userId + " not found");

        teamDto.setTeamId(utils.generateTeamId(30));
        teamDto.setStatus("created");

        TeamEntity teamEntity = modelMapper.map(teamDto, TeamEntity.class);


        TeamMemberEntity teamCreator = new TeamMemberEntity();
        teamCreator.setTeamMemberId(utils.generateTeamMemberId(30));
        teamCreator.setRole("Team Creator");
        teamCreator.setUser(userEntity);
        teamCreator.setTeam(teamEntity);

        List<TeamMemberEntity> members = new ArrayList<>();
        members.add(teamCreator);

        teamEntity.setTeamMembers(members);

        teamRepository.save(teamEntity);
        teamMemberRepository.save(teamCreator);

        TeamDto returnTeam = modelMapper.map(teamEntity, TeamDto.class);

        return returnTeam;




    }
}
