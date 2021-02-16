package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ProjectDto;

import java.util.List;

public interface ProjectService {


    ProjectDto getProjectByProjectId(String projectId);
    ProjectDto createProject(String userId, ProjectDto projectDto);
    ProjectDto updateProject(String projectId, ProjectDto projectDto);
    List<ProjectDto> getAllProjects();
    void deleteProjects(String projectId);

    List<ProjectDto> getProjectsByUserId(String userId);
}
