package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum TeamMemberEnum {

    ROLE_TEAM_CREATOR("Team Creator"),
    ROLE_TEAM_MEMBER("Team Member"),
    ROLE_SUPERVISOR("Supervisor"),
    STATUS_ACCEPTED("Accepted"),
    STATUS_WAITING("Waiting for acceptation");

    private String teamMemberEnum;

    TeamMemberEnum(String teamMemberEnum) {
        this.teamMemberEnum = teamMemberEnum;
    }

    public String getTeamMemberEnum() {
        return teamMemberEnum;
    }

    public void setTeamMemberEnum(String teamMemberEnum) {
        this.teamMemberEnum = teamMemberEnum;
    }
}
