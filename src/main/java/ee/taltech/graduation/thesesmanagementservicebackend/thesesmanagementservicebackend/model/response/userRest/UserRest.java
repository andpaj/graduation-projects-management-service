package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.userRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.groupRest.GroupRest;

import java.util.List;

public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String starterTeam;
    private String graduationLevel;
    private String confirmedProject;
    private List<GroupRest> groupEntities;
    private List<String> roles;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GroupRest> getGroupEntities() {
        return groupEntities;
    }

    public void setGroupEntities(List<GroupRest> groupEntities) {
        this.groupEntities = groupEntities;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getStarterTeam() {
        return starterTeam;
    }

    public void setStarterTeam(String starterTeam) {
        this.starterTeam = starterTeam;
    }

    public String getGraduationLevel() {
        return graduationLevel;
    }

    public void setGraduationLevel(String graduationLevel) {
        this.graduationLevel = graduationLevel;
    }

    public String getConfirmedProject() {
        return confirmedProject;
    }

    public void setConfirmedProject(String confirmedProject) {
        this.confirmedProject = confirmedProject;
    }
}
