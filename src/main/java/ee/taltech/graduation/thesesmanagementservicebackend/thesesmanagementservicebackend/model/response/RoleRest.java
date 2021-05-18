package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.userRest.UserRest;

import java.util.List;

public class RoleRest {

    private String name;
    private List<UserRest> users;
    private List<AuthorityRest> authorities;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRest> getUsers() {
        return users;
    }

    public void setUsers(List<UserRest> users) {
        this.users = users;
    }

    public List<AuthorityRest> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityRest> authorities) {
        this.authorities = authorities;
    }
}
