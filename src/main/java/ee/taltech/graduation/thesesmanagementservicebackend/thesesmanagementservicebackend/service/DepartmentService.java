package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentWithUsersDto;
import java.util.List;

public interface DepartmentService {

    DepartmentWithUsersDto createDepartment(DepartmentWithUsersDto departmentWithUsersDto);
    DepartmentWithUsersDto getDepartmentByDepartmentId(String departmentId);
    List<DepartmentDto> getDepartments();
    List<DepartmentWithUsersDto> getDepartmentsWithUsers();
    void deleteDepartment(String departmentId);
}
