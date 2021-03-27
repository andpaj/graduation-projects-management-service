package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import java.util.List;

public class TeamMemberWithTeam {

    private String teamMemberId;
    private String role;
    private String status;
    private UserTeamMemberRest user;
    private TeamRestWithoutMembers team;

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

    public TeamRestWithoutMembers getTeam() {
        return team;
    }

    public void setTeam(TeamRestWithoutMembers team) {
        this.team = team;
    }
}
