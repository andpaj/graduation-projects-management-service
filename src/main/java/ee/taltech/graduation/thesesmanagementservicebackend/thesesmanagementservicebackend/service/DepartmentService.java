package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentWithUsersDto);
    DepartmentDto getDepartmentByDepartmentId(String departmentId);
    List<DepartmentDto> getDepartments();
    List<DepartmentDto> getDepartmentsWithUsers();
    void deleteDepartment(String departmentId);
}
