package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum TeamMemberEnum {

    ROLE_TEAM_CREATOR("team_owner"),
    ROLE_TEAM_MEMBER("team_member"),
    ROLE_SUPERVISOR("supervisor"),
    ROLE_COSUPERVISOR("co_supervisor"),
    STATUS_ACCEPTED("accepted"),
    STATUS_WAITING("waiting");

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
