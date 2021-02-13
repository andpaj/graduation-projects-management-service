package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;

import java.util.List;

public class DepartmentWithUsersDto {

    private long id;
    private String departmentId;
    private String departmentName;
    private List<UserWithProjectsDto> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<UserWithProjectsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProjectsDto> users) {
        this.users = users;
    }
}
