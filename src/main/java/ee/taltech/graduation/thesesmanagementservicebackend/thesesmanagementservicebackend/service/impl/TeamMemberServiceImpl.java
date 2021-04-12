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
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamMemberEnum;
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
    public TeamMemberDto getTeamMemberById(String teamMemberId) {
        ModelMapper modelMapper = new ModelMapper();

        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByTeamMemberId(teamMemberId);
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
        newMember.setStatus(TeamMemberEnum.STATUS_WAITING.getTeamMemberEnum());
        newMember.setRole(TeamMemberEnum.ROLE_TEAM_MEMBER.getTeamMemberEnum());
        newMember.setTeam(teamEntity);
        newMember.setUser(userEntity);

        TeamMemberEntity savedMember = teamMemberRepository.save(newMember);

        TeamMemberDto teamMemberDto = modelMapper.map(savedMember, TeamMemberDto.class);

        return teamMemberDto;

    }

    @Override
    public TeamMemberDto acceptMembership(String userId, String teamMemberId) {

        boolean active = true;

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByTeamMemberId(teamMemberId);
        if (teamMemberEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM_MEMBER.getErrorMessage());

        if (teamMemberEntity.getStatus() == TeamMemberEnum.STATUS_ACCEPTED.getTeamMemberEnum()){
            throw new ServiceException(ErrorMessages.MEMBER_ALREADY_ACCEPTED.getErrorMessage());
        }


        teamMemberEntity.setStatus(TeamMemberEnum.STATUS_ACCEPTED.getTeamMemberEnum());

        TeamEntity teamEntity = teamMemberEntity.getTeam();

        for (TeamMemberEntity teamMember: teamEntity.getTeamMembers()){
            if (teamMember.getStatus() == TeamMemberEnum.STATUS_WAITING.getTeamMemberEnum()){
                active = false;
            }
        }

        if (active){
            teamEntity.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        }

        teamRepository.save(teamEntity);


        TeamMemberEntity savedStatus = teamMemberRepository.save(teamMemberEntity);

        ModelMapper modelMapper = new ModelMapper();
        TeamMemberDto teamMemberDto = modelMapper.map(savedStatus, TeamMemberDto.class);

        return teamMemberDto;

    }


    @Override
    public void declineMembership(String userId, String teamMemberId) {

        boolean active = true;

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        TeamMemberEntity teamMemberEntity = teamMemberRepository.findByTeamMemberId(teamMemberId);
        if (teamMemberEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM_MEMBER.getErrorMessage());

        TeamEntity teamEntity = teamMemberEntity.getTeam();

        teamMemberRepository.delete(teamMemberEntity);

        if (teamEntity.getTeamMembers().size() == 1){
            teamEntity.setStatus(TeamEnum.STATUS_NOT_ACTIVE.getTeamEnum());
            teamRepository.save(teamEntity);
            return;
        }

        for (TeamMemberEntity teamMember: teamEntity.getTeamMembers()){
            if (teamMember.getStatus() == TeamMemberEnum.STATUS_WAITING.getTeamMemberEnum()){
                active = false;
            }
        }

        if (active){
            teamEntity.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        }
        teamRepository.save(teamEntity);

    }
}
