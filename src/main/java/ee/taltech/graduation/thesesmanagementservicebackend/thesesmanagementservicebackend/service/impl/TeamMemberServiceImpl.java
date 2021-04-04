package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamMemberEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamMemberRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamMemberService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamMemberDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    Utils utils;


    @Override
    public TeamMemberDto getTeamMemberById(String id) {
        ModelMapper modelMapper = new ModelMapper();

        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByTeamMemberId(id);
        if (teamMemberEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM_MEMBER.getErrorMessage());

        TeamMemberDto teamMemberDto = modelMapper.map(teamMemberEntity, TeamMemberDto.class);

        return teamMemberDto;


    }

    @Override
    public TeamMemberDto addMemberToTeam(String userId, String teamId) {
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM.getErrorMessage());

        TeamMemberEntity newMember = new TeamMemberEntity();
        newMember.setTeamMemberId(utils.generateTeamMemberId(30));
        newMember.setStatus("Waiting for acceptation");
        newMember.setRole("Guest user");
        newMember.setTeam(teamEntity);
        newMember.setUser(userEntity);

        TeamMemberEntity savedMember = teamMemberRepository.save(newMember);

        TeamMemberDto teamMemberDto = modelMapper.map(savedMember, TeamMemberDto.class);

        return teamMemberDto;

    }

    @Override
    public TeamMemberDto acceptMembership(String userId, String teamMemberId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByTeamMemberId(teamMemberId);
        if (teamMemberEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM_MEMBER.getErrorMessage());

        teamMemberEntity.setStatus("accepted");
        TeamMemberEntity savedStatus = teamMemberRepository.save(teamMemberEntity);

        ModelMapper modelMapper = new ModelMapper();
        TeamMemberDto teamMemberDto = modelMapper.map(savedStatus, TeamMemberDto.class);

        return teamMemberDto;

    }

    @Override
    public void declineMembership(String userId, String teamMemberId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByTeamMemberId(teamMemberId);
        if (teamMemberEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM_MEMBER.getErrorMessage());

        teamMemberRepository.delete(teamMemberEntity);

    }
}
