package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.TagRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ThesisDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TagDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ThesisRequestToDto implements Converter<ThesisDetailsRequestModel, ThesisDto>
{

    @Autowired
    Utils utils;

    @Override
    public ThesisDto convert(ThesisDetailsRequestModel thesisDetailsRequestModel) {

        ModelMapper modelMapper = new ModelMapper();

        ThesisDto thesisDto = new ThesisDto();
        thesisDto.setThesisId(utils.generateThesisId(30));
        thesisDto.setStatus("free to take");
        thesisDto.setCreatingTime(new Date());
        thesisDto.setLanguage(thesisDetailsRequestModel.getLanguage());
        thesisDto.setTitle(thesisDetailsRequestModel.getTitle());
        thesisDto.setDescription(thesisDetailsRequestModel.getDescription());
        thesisDto.setStudentAmount(thesisDetailsRequestModel.getStudentAmount());
        thesisDto.setDegree(thesisDetailsRequestModel.getDegree());
        thesisDto.setDifficultyRating(thesisDetailsRequestModel.getDifficultyRating());

//        for (TagRequestModel tagRequestModel: thesisDetailsRequestModel.getTags()){
//           TagDto tagDto = modelMapper.map(tagRequestModel, TagDto.class);
//           tagDto.setTagId(utils.generateDepartmentId(30));
//
//        }




        return thesisDto;

    }
}
