package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;

import java.util.List;

public interface ThesisService {


    ThesisDto getThesisByThesisId(String thesisId);
    ThesisDto createThesis(String userId, ThesisDto thesisDto);
    ThesisDto updateThesis(String thesisId, ThesisDto thesisDto);
    List<ThesisDto> getAllThesis();
    void deleteThesis(String thesisId);

}
