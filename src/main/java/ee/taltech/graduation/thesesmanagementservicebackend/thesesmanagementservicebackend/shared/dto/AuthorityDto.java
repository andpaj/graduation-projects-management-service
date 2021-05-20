package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;


import java.util.List;

public class AuthorityDto {

    private long id;
    private String name;
    private List<RoleDto> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
