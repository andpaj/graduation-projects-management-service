package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;

public interface ThesisService {


    ThesisDto getThesisByThesisId(String thesisId);
    ThesisDto createThesis(String userId, ThesisDto thesisDto);
    ThesisDto updateThesis(String thesisId, ThesisDto thesisDto);
    void deleteThesis(String thesisId);

}
