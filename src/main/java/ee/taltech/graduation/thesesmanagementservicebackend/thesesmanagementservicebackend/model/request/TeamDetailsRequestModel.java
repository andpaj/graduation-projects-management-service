package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TeamDetailsRequestModel {

    @NotNull(message = "Team name cannot be null")
    private String teamName;
    @NotNull(message = "Users cannot be null")
    private List<String> users;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
