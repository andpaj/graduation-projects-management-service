package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
import java.util.List;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByDepartmentId(String departmentId);
    List<DepartmentDto> getDepartments();
    void deleteDepartment(String departmentId);
}
