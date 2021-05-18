package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.Roles;

import java.util.List;

public class AuthorityRest {

    private long id;
    private String name;


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
    
}
