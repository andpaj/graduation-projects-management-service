package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByDepartmentId(String departmentId);

}
