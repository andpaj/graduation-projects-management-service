package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ApplicationRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ProjectRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ApplicationService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    Utils utils;

    @Override
    public ApplicationDto getApplicationByApplicationId(String id) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(id);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        ApplicationDto applicationDto = modelMapper.map(applicationEntity, ApplicationDto.class);

        return applicationDto;

    }

    @Override
    public List<ApplicationDto> getAllApplicationsByUserId(String userId) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new ServiceException("User with Id " + userId + " not found");

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
        if (userEntity == null) throw new ServiceException("User with Id " + supervisorId + " not found");

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
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        UserEntity userEntity = userRepository.findByUserId(supervisorId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        if (!applicationEntity.getStatus().equals("sent")){
            throw new ServiceException("This application is already accepted or declined by supervisor");
        }

        if (!applicationEntity.getProject().getUser().getUserId().equals(supervisorId)){
            throw new ServiceException("You have no rights to run this method");
        }

        applicationEntity.setStatus("accepted");

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        ApplicationDto applicationDto = modelMapper.map(savedApplication, ApplicationDto.class);

        return applicationDto;


    }

    @Override
    public ApplicationDto declineApplicationFromSupervisorSide(String supervisorId, String applicationId) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
        if (applicationEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        UserEntity userEntity = userRepository.findByUserId(supervisorId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        if (!applicationEntity.getStatus().equals("sent")){
            throw new ServiceException("This application is already accepted or declined by supervisor");
        }

        if (!applicationEntity.getProject().getUser().getUserId().equals(supervisorId)){
            throw new ServiceException("You have no rights to run this method");
        }

        applicationEntity.setStatus("declined");

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        ApplicationDto applicationDto = modelMapper.map(savedApplication, ApplicationDto.class);

        return applicationDto;
    }

    @Override
    public ApplicationDto createApplication(String projectId, String teamId, ApplicationDto applicationDto) {

        ModelMapper modelMapper = new ModelMapper();

        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        applicationDto.setApplicationId(utils.generateApplicationId(30));
        applicationDto.setStatus("sent");
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
}
