package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;

import java.util.List;

public class UserDto {
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String starterTeam;
    private List<ProjectDto> projects;
    private List<GroupDto> groupEntities;
    private List<String> roles;
    private List<TeamMemberDto> teamMembers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }

    public List<GroupDto> getGroupEntities() {
        return groupEntities;
    }

    public void setGroupEntities(List<GroupDto> groupEntities) {
        this.groupEntities = groupEntities;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<TeamMemberDto> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMemberDto> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getStarterTeam() {
        return starterTeam;
    }

    public void setStarterTeam(String starterTeam) {
        this.starterTeam = starterTeam;
    }
}
