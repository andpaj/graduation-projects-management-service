package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ThesisDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ThesisRequestToDto implements Converter<ThesisDetailsRequestModel, ThesisDto>
{
    @Override
    public ThesisDto convert(ThesisDetailsRequestModel thesisDetailsRequestModel) {

        ThesisDto thesisDto = new ThesisDto();
        thesisDto.setThesisId("test");
        thesisDto.setTeacherId("teacherTest");
        thesisDto.setApplicationId("null");
        thesisDto.setStatus("free to take");
        thesisDto.setCreatingTime(new Date());
        thesisDto.setLanguage(thesisDetailsRequestModel.getLanguage());
        thesisDto.setTitle(thesisDetailsRequestModel.getTitle());
        thesisDto.setDescription(thesisDetailsRequestModel.getDescription());
        thesisDto.setStudentAmount(thesisDetailsRequestModel.getStudentAmount());
        thesisDto.setDegree(thesisDetailsRequestModel.getDegree());
        thesisDto.setDifficultyRating(thesisDetailsRequestModel.getDifficultyRating());


        return thesisDto;

    }
}
