package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

public class TeamRestWithoutMembers {

    private String teamId;
    private String teamName;
    private String status;

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
}