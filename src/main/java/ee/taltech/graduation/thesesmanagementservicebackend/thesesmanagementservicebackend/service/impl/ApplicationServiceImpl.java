package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ApplicationEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ProjectEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TeamEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ApplicationRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ProjectRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ApplicationService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ProjectRepository projectRepository;

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
    public ApplicationDto createApplication(String projectId, String teamId, ApplicationDto applicationDto) {

        ModelMapper modelMapper = new ModelMapper();

        TeamEntity teamEntity = teamRepository.findByTeamId(teamId);
        if (teamEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        applicationDto.setApplicationId(utils.generateApplicationId(30));
        applicationDto.setStatus("Waiting for acceptation");
        applicationDto.setCreatingTime(new Date());

        ApplicationEntity applicationEntity = modelMapper.map(applicationDto, ApplicationEntity.class);

        applicationEntity.setProject(projectEntity);
        applicationEntity.setTeam(teamEntity);

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        ApplicationDto returnApplication  = modelMapper.map(savedApplication, ApplicationDto.class);

        return returnApplication;


    }
}
