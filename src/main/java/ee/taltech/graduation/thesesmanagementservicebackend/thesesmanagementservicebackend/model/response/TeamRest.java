package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import java.util.ArrayList;
import java.util.List;

public class TeamRest {

    private String teamId;
    private String teamName;
    private String status;
    private List<TeamMemberRest> teamMembers = new ArrayList<>();


    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TeamMemberRest> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMemberRest> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
