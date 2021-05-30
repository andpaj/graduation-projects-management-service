package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamMemberEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ApplicationRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamMemberRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TeamService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TeamDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamMemberEnum;
import org.apache.catalina.User;
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
    ApplicationRepository applicationRepository;

    @Autowired
    Utils utils;

    @Override
    public TeamDto createTeam(String userId, TeamDto teamDto, List<String> teamMembers) {

        ModelMapper modelMapper = new ModelMapper();

        List<UserEntity> additionalMembers = new ArrayList<>();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());


        if (teamMembers != null ) {
            for (String user : teamMembers) {
                UserEntity foundUser = userRepository.findByUserId(user);
                if (foundUser == null) throw
                        new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());
                additionalMembers.add(foundUser);
            }
        }

        if (userEntity.getConfirmedProject() != null){
            throw new ServiceException(ErrorMessages.AlREADY_HAVE_CONFIRMED_PROJECT.getErrorMessage());
        }

        for (UserEntity additionalUser: additionalMembers){
            if (additionalUser.getConfirmedProject() != null) {
                throw new ServiceException(ErrorMessages.AlREADY_HAVE_CONFIRMED_PROJECT.getErrorMessage());
            }

        }


        teamDto.setTeamId(utils.generateTeamId(30));
        teamDto.setStatus(TeamEnum.STATUS_NOT_ACTIVE.getTeamEnum());
        teamDto.setAuthorId(userEntity.getUserId());

        TeamEntity teamEntity = modelMapper.map(teamDto, TeamEntity.class);

        TeamMemberEntity teamCreator = new TeamMemberEntity();
        teamCreator.setTeamMemberId(utils.generateTeamMemberId(30));
        teamCreator.setRole(TeamMemberEnum.ROLE_TEAM_CREATOR.getTeamMemberEnum());
        teamCreator.setUser(userEntity);
        teamCreator.setTeam(teamEntity);
        teamCreator.setStatus(TeamMemberEnum.STATUS_ACCEPTED.getTeamMemberEnum());

        List<TeamMemberEntity> members = new ArrayList<>();
        members.add(teamCreator);

        for (UserEntity additionalMember: additionalMembers){
            TeamMemberEntity memberEntity = new TeamMemberEntity();
            memberEntity.setTeamMemberId(utils.generateTeamMemberId(30));
            memberEntity.setRole(TeamMemberEnum.ROLE_TEAM_MEMBER.getTeamMemberEnum());
            memberEntity.setStatus(TeamMemberEnum.STATUS_WAITING.getTeamMemberEnum());
            memberEntity.setUser(additionalMember);
            memberEntity.setTeam(teamEntity);
            members.add(memberEntity);
        }


        teamEntity.setTeamMembers(members);

        teamRepository.save(teamEntity);
        teamMemberRepository.saveAll(members);


        TeamDto returnTeam = modelMapper.map(teamEntity, TeamDto.class);

        return returnTeam;

    }

    @Override
    public TeamDto getTeamById(String teamId) {
        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM.getErrorMessage());
        ModelMapper modelMapper  = new ModelMapper();
        TeamDto teamDto = modelMapper.map(teamEntity, TeamDto.class);

        return teamDto;
    }

    @Override
    public List<TeamDto> getTeamsByUserId(String userId) {

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        List<TeamEntity> teamsEntities = new ArrayList<>();

        for (TeamMemberEntity teamMemberEntity : userEntity.getTeamMembers()){
            teamsEntities.add(teamMemberEntity.getTeam());
        }

        List<TeamDto> teamDtos = new ArrayList<>();

        for (TeamEntity teamEntity : teamsEntities){
            TeamDto teamDto = modelMapper.map(teamEntity, TeamDto.class);
            teamDtos.add(teamDto);
        }

        return teamDtos;
    }

    @Override
    public TeamDto finishTeamProject(String teamId) {

        ModelMapper modelMapper = new ModelMapper();
        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM.getErrorMessage());

        teamEntity.setStatus(TeamEnum.STATUS_PROJECT_IS_FINISHED.getTeamEnum());
        TeamEntity savedTeam = teamRepository.save(teamEntity);

        TeamDto teamDto = modelMapper.map(savedTeam, TeamDto.class);

        return teamDto;

    }

    @Override
    public void deleteTeam(String teamId) {

        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM.getErrorMessage());


        teamRepository.delete(teamEntity);


    }


}
