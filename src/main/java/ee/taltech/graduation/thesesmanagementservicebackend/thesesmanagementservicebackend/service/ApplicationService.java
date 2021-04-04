package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ApplicationDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApplicationService {
    ApplicationDto createApplication(String projectId, String teamId, ApplicationDto applicationDto);
    ApplicationDto getApplicationByApplicationId(String id);
    List<ApplicationDto> getAllApplicationsByUserId(String userId);
    List<ApplicationDto> getAllApplicationsBySupervisorId(String supervisorId);
    ApplicationDto acceptApplicationFromSupervisorSide(String supervisorId, String applicationId);
    ApplicationDto declineApplicationFromSupervisorSide(String supervisorId, String applicationId);
    ApplicationDto acceptApplicationFromStudentSide(String studentId, String applicationId);
    ApplicationDto declineApplicationFromStudentSide(String studentId, String applicationId);
}
