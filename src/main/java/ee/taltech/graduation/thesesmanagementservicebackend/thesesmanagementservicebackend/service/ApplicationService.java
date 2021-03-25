package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import org.springframework.stereotype.Service;


public interface ApplicationService {
    ApplicationDto createApplication(String projectId, String teamId, ApplicationDto applicationDto);

    ApplicationDto getApplicationByApplicationId(String id);
}
