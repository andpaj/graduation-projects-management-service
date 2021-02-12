package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ProjectDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProjectRequestToDto implements Converter<ProjectDetailsRequestModel, ProjectDto>
{

    @Autowired
    Utils utils;

    @Override
    public ProjectDto convert(ProjectDetailsRequestModel projectDetailsRequestModel) {

        ModelMapper modelMapper = new ModelMapper();

        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(utils.generateProjectId(30));
        projectDto.setStatus("free to take");
        projectDto.setCreatingTime(new Date());
        projectDto.setLanguage(projectDetailsRequestModel.getLanguage());
        projectDto.setTitle(projectDetailsRequestModel.getTitle());
        projectDto.setDescription(projectDetailsRequestModel.getDescription());
        projectDto.setStudentAmount(projectDetailsRequestModel.getStudentAmount());
        projectDto.setDegree(projectDetailsRequestModel.getDegree());
        projectDto.setDifficultyRating(projectDetailsRequestModel.getDifficultyRating());

//        for (TagRequestModel tagRequestModel: thesisDetailsRequestModel.getTags()){
//           TagDto tagDto = modelMapper.map(tagRequestModel, TagDto.class);
//           tagDto.setTagId(utils.generateDepartmentId(30));
//
//        }




        return projectDto;

    }
}
