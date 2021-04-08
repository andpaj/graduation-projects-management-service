package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ProjectEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TagEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ProjectRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TagRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ProjectService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ProjectDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TagDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.ProjectEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    Utils utils;


    @Override
    public ProjectDto getProjectByProjectId(String projectId) {

        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_PROJECT.getErrorMessage());
        ModelMapper modelMapper  = new ModelMapper();
        ProjectDto projectDto = modelMapper.map(projectEntity, ProjectDto.class);

        return projectDto;

    }

    @Override
    public List<ProjectDto> getAllProjects() {

        ModelMapper modelMapper = new ModelMapper();

        List<ProjectDto> projectDtoList = new ArrayList<>();
        List<ProjectEntity> projectEntities = projectRepository.findAll();

        for (ProjectEntity projectEntity : projectEntities){

            ProjectDto projectDto = modelMapper.map(projectEntity, ProjectDto.class);
            projectDtoList.add(projectDto);
        }

        return projectDtoList;

    }

    @Override
    public List<ProjectDto> getProjectsByUserId(String userId) {
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        List<ProjectDto> projectDtoList = new ArrayList<>();
        List<ProjectEntity> projectEntities = projectRepository.findByUser(userEntity);

        for (ProjectEntity projectEntity : projectEntities){

            ProjectDto projectDto = modelMapper.map(projectEntity, ProjectDto.class);
            projectDtoList.add(projectDto);
        }

        return projectDtoList;

    }

    @Override
    public ProjectDto createProject(String userId, ProjectDto projectDto) {

        projectDto.setProjectId(utils.generateProjectId(30));
        projectDto.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
        projectDto.setCreatingTime(new Date());
        ModelMapper modelMapper = new ModelMapper();

        ProjectEntity projectEntity = projectDtoToEntity(projectDto);

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        userEntity.getProjects().add(projectEntity);
        projectEntity.setUser(userEntity);
        userRepository.save(userEntity);



        ProjectDto returnValue = modelMapper.map(projectEntity, ProjectDto.class);
        return returnValue;

    }

    @Override
    public ProjectDto updateProject(String projectId, ProjectDto projectDto) {

        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_PROJECT.getErrorMessage());

        projectEntity.setLanguage(projectDto.getLanguage());
        projectEntity.setTitle(projectDto.getTitle());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setStudentAmount(projectDto.getStudentAmount());
        projectEntity.setDegree(projectDto.getDegree());

        ProjectEntity updatedProject = projectRepository.save(projectEntity);

        ModelMapper modelMapper = new ModelMapper();
        ProjectDto returnValue = modelMapper.map(updatedProject, ProjectDto.class);

        return  returnValue;

    }

    @Override
    public void deleteProjects(String projectId) {
        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_PROJECT.getErrorMessage());

        projectRepository.delete(projectEntity);
    }

    public ProjectEntity projectDtoToEntity(ProjectDto projectDto) {

        ModelMapper modelMapper = new ModelMapper();

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectId(projectDto.getProjectId());
        projectEntity.setStatus(projectDto.getStatus());
        projectEntity.setCreatingTime(projectDto.getCreatingTime());
        projectEntity.setLanguage(projectDto.getLanguage());
        projectEntity.setTitle(projectDto.getTitle());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setStudentAmount(projectDto.getStudentAmount());
        projectEntity.setDegree(projectDto.getDegree());
        if (projectDto.getTags() != null && !projectDto.getTags().isEmpty()) {
            for (TagDto tagDto: projectDto.getTags()){
                if (tagRepository.findByTagName(tagDto.getTagName()) == null) {
                    tagDto.setTagId(utils.generateTagId(30));
                    TagEntity tagEntity = modelMapper.map(tagDto, TagEntity.class);
                    projectEntity.getTags().add(tagEntity);
                } else {

                    TagEntity tagEntity = tagRepository.findByTagName(tagDto.getTagName());
                    projectEntity.getTags().add(tagEntity);
                }
            }
        }

        return projectEntity;


    }
}
