package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;

public interface ThesisService {


    ThesisDto getThesisByThesisId(String thesisId);
    ThesisDto createThesis(String userId, ThesisDto thesisDto);

}
