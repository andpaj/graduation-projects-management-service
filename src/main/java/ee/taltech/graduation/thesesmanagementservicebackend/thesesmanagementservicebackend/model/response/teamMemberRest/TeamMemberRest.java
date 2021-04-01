package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamMemberRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.userRest.UserTeamMemberRest;

public class TeamMemberRest {

    private String teamMemberId;
    private String role;
    private String status;
    private UserTeamMemberRest user;

    public String getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(String teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserTeamMemberRest getUser() {
        return user;
    }

    public void setUser(UserTeamMemberRest user) {
        this.user = user;
    }
}
