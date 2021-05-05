package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ProjectEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TagEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
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

import java.util.*;

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

    @Autowired
    GroupRepository groupRepository;


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
    public List<ProjectDto> getProjectsByGroupId(String groupId) {
        ModelMapper modelMapper = new ModelMapper();

        List<ProjectEntity> allProjects = projectRepository.findAll();
        List<ProjectEntity> sortedProjects = new ArrayList<>();
        List<ProjectDto> sortedProjectsDto = new ArrayList<>();

        for (ProjectEntity projectEntity: allProjects){
            for (GroupEntity groupEntity: projectEntity.getGroupEntities()){
                if (groupEntity.getGroupId().equals(groupId)){
                    sortedProjects.add(projectEntity);
                }
            }
        }

        for (ProjectEntity project: sortedProjects){
            ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
            sortedProjectsDto.add(projectDto);
        }


        return sortedProjectsDto;

    }

    @Override
    public List<ProjectDto> getCoSupervisingProjects(String userId) {
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        List<ProjectDto> projectDtoList = new ArrayList<>();
        List<ProjectEntity> coSupervisingProjectsEntities = userEntity.getCoSupervisorProjects();

        for (ProjectEntity projectEntity : coSupervisingProjectsEntities){

            ProjectDto projectDto = modelMapper.map(projectEntity, ProjectDto.class);
            projectDtoList.add(projectDto);
        }

        return projectDtoList;
    }

    @Override
    public ProjectDto createProject(String userId,
                                    List<String> groupsId,
                                    List<String> coSupervisors,
                                    ProjectDto projectDto) {

        List<GroupEntity> projectGroups = new ArrayList<>();
        List<UserEntity> coSupervisorsEntities = new ArrayList<>();

        for (String groupId: groupsId){
            GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
            if (groupEntity == null) throw
                    new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());
            projectGroups.add(groupEntity);
        }

        if (!coSupervisors.isEmpty()) {
            for (String supervisor : coSupervisors) {
                UserEntity userEntity = userRepository.findByUserId(supervisor);
                if (userEntity == null) throw
                        new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());
                coSupervisorsEntities.add(userEntity);
            }
        }


        projectDto.setProjectId(utils.generateProjectId(30));
        projectDto.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
        projectDto.setCreatingTime(new Date());
        ModelMapper modelMapper = new ModelMapper();

        ProjectEntity projectEntity = projectDtoToEntity(projectDto);
        projectEntity.setGroupEntities(projectGroups);
        projectEntity.setCoSupervisors(coSupervisorsEntities);


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
        ModelMapper modelMapper = new ModelMapper();

        ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
        if (projectEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_PROJECT.getErrorMessage());

        projectEntity.setLanguage(projectDto.getLanguage());
        projectEntity.setTitle(projectDto.getTitle());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setStudentAmount(projectDto.getStudentAmount());
        projectEntity.setDegree(projectDto.getDegree());

        if (projectDto.getTags() != null && !projectDto.getTags().isEmpty()) {
            projectEntity.setTags(new HashSet<>());
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
        } else {
            projectEntity.setTags(new HashSet<>());
        }

        ProjectEntity updatedProject = projectRepository.save(projectEntity);

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
