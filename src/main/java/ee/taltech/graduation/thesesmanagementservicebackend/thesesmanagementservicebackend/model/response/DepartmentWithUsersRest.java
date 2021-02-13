package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import java.util.List;

public class DepartmentWithUsersRest {

    private String departmentId;
    private String departmentName;
    private List<UserRest> users;

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


    public List<UserRest> getUsers() {
        return users;
    }

    public void setUsers(List<UserRest> users) {
        this.users = users;
    }
}


