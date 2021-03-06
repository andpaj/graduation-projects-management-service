package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ApplicationService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.ApplicationEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.ProjectEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamMemberEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    Utils utils;

    @Override
    public ApplicationDto getApplicationByApplicationId(String applicationId) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_APPLICATION.getErrorMessage());

        ApplicationDto applicationDto = modelMapper.map(applicationEntity, ApplicationDto.class);

        return applicationDto;

    }

    @Override
    public List<ApplicationDto> getAllApplicationsByUserId(String userId) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        List<ApplicationEntity> applicationEntityList = new ArrayList<>();

        for (TeamMemberEntity teamMemberEntity: userEntity.getTeamMembers()){
            applicationEntityList.addAll(teamMemberEntity.getTeam().getApplications());
        }

        List<ApplicationDto> applicationDtoList = new ArrayList<>();

        for (ApplicationEntity applicationEntity: applicationEntityList){
            ApplicationDto applicationDto = modelMapper.map(applicationEntity, ApplicationDto.class);
            applicationDtoList.add(applicationDto);
        }

        return applicationDtoList;


    }

    @Override
    public List<ApplicationDto> getAllApplicationsBySupervisorId(String supervisorId) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = userRepository.findByUserId(supervisorId);
        if (userEntity == null) throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        List<ApplicationEntity> applicationEntityList = new ArrayList<>();

        for (ProjectEntity projectEntity: userEntity.getProjects()){
            applicationEntityList.addAll(projectEntity.getApplications());
        }

        List<ApplicationDto> applicationDtoList = new ArrayList<>();

        for (ApplicationEntity applicationEntity: applicationEntityList){
            ApplicationDto applicationDto = modelMapper.map(applicationEntity, ApplicationDto.class);
            applicationDtoList.add(applicationDto);
        }

        return applicationDtoList;

    }
    @Override
    public ApplicationDto acceptApplicationFromSupervisorSide(String supervisorId, String applicationId) {

        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_APPLICATION.getErrorMessage());

        UserEntity userEntity = userRepository.findByUserId(supervisorId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());


        if (!applicationEntity.getStatus().equals(ApplicationEnum.SENT.getApplicationEnum())){
            throw new ServiceException(ErrorMessages.APPLICATION_ACCEPTED_DECLINED_BY_SUPERVISOR.getErrorMessage());
        }

        if (!applicationEntity.getProject().getUser().getUserId().equals(supervisorId)){
            throw new ServiceException(ErrorMessages.APPLICATION_NO_RIGHTS.getErrorMessage());
        }

        applicationEntity.setStatus(ApplicationEnum.ACCEPTED.getApplicationEnum());

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        ApplicationDto applicationDto = modelMapper.map(savedApplication, ApplicationDto.class);

        return applicationDto;


    }

    @Override
    public ApplicationDto declineApplicationFromSupervisorSide(String supervisorId, String applicationId) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_APPLICATION.getErrorMessage());

        UserEntity userEntity = userRepository.findByUserId(supervisorId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());


        if (!applicationEntity.getStatus().equals(ApplicationEnum.SENT.getApplicationEnum())){
            throw new ServiceException(ErrorMessages.APPLICATION_ACCEPTED_DECLINED_BY_SUPERVISOR.getErrorMessage());
        }

        if (!applicationEntity.getProject().getUser().getUserId().equals(supervisorId)){
            throw new ServiceException(ErrorMessages.APPLICATION_NO_RIGHTS.getErrorMessage());
        }

        applicationEntity.setStatus(ApplicationEnum.DECLINED.getApplicationEnum());

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        ApplicationDto applicationDto = modelMapper.map(savedApplication, ApplicationDto.class);

        return applicationDto;
    }
    @Override
    public ApplicationDto acceptApplicationFromStudentSide(String studentId, String applicationId) {

        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_APPLICATION.getErrorMessage());

        UserEntity userEntity = userRepository.findByUserId(studentId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        if (!applicationEntity.getStatus().equals(ApplicationEnum.ACCEPTED.getApplicationEnum())){
            throw new ServiceException(ErrorMessages.APPLICATION_CANT_CONFIRMED_BY_STUDENT.getErrorMessage());
        }

        //Set application status
        applicationEntity.setStatus(ApplicationEnum.PROJECT_IN_PROGRESS.getApplicationEnum());

        //Set project to the team
        TeamEntity teamEntity = applicationEntity.getTeam();
        teamEntity.setProject(applicationEntity.getProject());
        teamEntity.setStatus(TeamEnum.STATUS_PROJECT_IN_PROGRESS.getTeamEnum());

        //Set team to the project
        ProjectEntity projectEntity = applicationEntity.getProject();
        projectEntity.setTeam(teamEntity);
        projectEntity.setStatus(ProjectEnum.STATUS_NOT_AVAILABLE.getProjectEnum());
        projectEntity.setAcceptingTime(new Date());

        //Set confirmed project id to all team members
        for (TeamMemberEntity teamMember: teamEntity.getTeamMembers()){
            teamMember.getUser().setConfirmedProject(projectEntity.getProjectId());
        }

        //Add supervisor as a team member to the team
        TeamMemberEntity supervisorTeamMember = new TeamMemberEntity();
        supervisorTeamMember.setTeamMemberId(utils.generateTeamMemberId(30));
        supervisorTeamMember.setTeam(teamEntity);
        supervisorTeamMember.setRole(TeamMemberEnum.ROLE_SUPERVISOR.getTeamMemberEnum());
        supervisorTeamMember.setUser(projectEntity.getUser());
        supervisorTeamMember.setStatus(TeamMemberEnum.STATUS_ACCEPTED.getTeamMemberEnum());

        //Add co supervisors as a team members to the team
        if (!projectEntity.getCoSupervisors().isEmpty()) {
            for (UserEntity coSupervisor : projectEntity.getCoSupervisors()) {
                TeamMemberEntity coSupervisorTeamMember = new TeamMemberEntity();
                coSupervisorTeamMember.setTeamMemberId(utils.generateTeamMemberId(30));
                coSupervisorTeamMember.setTeam(teamEntity);
                coSupervisorTeamMember.setRole(TeamMemberEnum.ROLE_COSUPERVISOR.getTeamMemberEnum());
                coSupervisorTeamMember.setUser(coSupervisor);
                coSupervisorTeamMember.setStatus(TeamMemberEnum.STATUS_ACCEPTED.getTeamMemberEnum());
                teamMemberRepository.save(coSupervisorTeamMember);
            }
        }


        //Remove all other teams of team members
        for (TeamMemberEntity teamMemberEntity: userEntity.getTeamMembers()){
            if (!teamMemberEntity.getTeam().getTeamId().equals(teamEntity.getTeamId())) {
                teamRepository.delete(teamMemberEntity.getTeam());
            }
        }


        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
        teamRepository.save(teamEntity);
        teamMemberRepository.save(supervisorTeamMember);
        projectRepository.save(projectEntity);



        ApplicationDto applicationDto = modelMapper.map(savedApplication, ApplicationDto.class);

        return applicationDto;

    }

    @Override
    public ApplicationDto declineApplicationFromStudentSide(String studentId, String applicationId) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_APPLICATION.getErrorMessage());

        UserEntity userEntity = userRepository.findByUserId(studentId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        if (!applicationEntity.getStatus().equals(ApplicationEnum.ACCEPTED.getApplicationEnum())){
            throw new ServiceException(ErrorMessages.APPLICATION_CANT_DECLINED_BY_STUDENT.getErrorMessage());
        }

        applicationEntity.setStatus(ApplicationEnum.DECLINED_BY_STUDENT.getApplicationEnum());

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        ApplicationDto applicationDto = modelMapper.map(savedApplication, ApplicationDto.class);

        return applicationDto;


    }
    @Override
    public ApplicationDto createApplication(String projectId, String teamId, ApplicationDto applicationDto) {

        ModelMapper modelMapper = new ModelMapper();

        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TEAM.getErrorMessage());

        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_PROJECT.getErrorMessage());

        for (TeamMemberEntity teamMemberEntity: teamEntity.getTeamMembers()){
            if (teamMemberEntity.getUser().getConfirmedProject() != null ){
                throw new ServiceException(ErrorMessages.AlREADY_HAVE_CONFIRMED_PROJECT.getErrorMessage());
            }
        }

        for (ApplicationEntity applicationEntity: teamEntity.getApplications()){
            if (applicationEntity.getProject().getProjectId().equals(projectId)) throw
                    new ServiceException(ErrorMessages.APPLICATION_IS_ALREADY_SENT.getErrorMessage());
        }

        if (!teamEntity.getStatus().equals(TeamEnum.STATUS_ACTIVE.getTeamEnum())){
            throw new ServiceException(ErrorMessages.APPLICATION_CANT_BE_SEND.getErrorMessage());
        }

        if (projectEntity.getStatus().equals(ProjectEnum.STATUS_NOT_AVAILABLE.getProjectEnum())){
            throw new ServiceException(ErrorMessages.PROJECT_IS_NOT_AVAILABLE.getErrorMessage());
        }

        if (projectEntity.getStudentAmount() < teamEntity.getTeamMembers().size()){
            throw new ServiceException(ErrorMessages.STUDENT_AMOUNT_HIGH.getErrorMessage());
        }


        applicationDto.setApplicationId(utils.generateApplicationId(30));
        applicationDto.setStatus(ApplicationEnum.SENT.getApplicationEnum());
        applicationDto.setCreatingTime(new Date());

        ApplicationEntity applicationEntity = modelMapper.map(applicationDto, ApplicationEntity.class);

        applicationEntity.setProject(projectEntity);
        applicationEntity.setTeam(teamEntity);

        List<ApplicationEntity> applicationEntityList = new ArrayList<>();
        applicationEntityList.add(applicationEntity);

        projectEntity.setApplications(applicationEntityList);
        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);


        ApplicationDto returnApplication  = modelMapper.map(savedApplication, ApplicationDto.class);

        return returnApplication;


    }




    @Override
    public void deleteApplication(String applicationId) {
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_APPLICATION.getErrorMessage());

        applicationRepository.delete(applicationEntity);


    }
}
