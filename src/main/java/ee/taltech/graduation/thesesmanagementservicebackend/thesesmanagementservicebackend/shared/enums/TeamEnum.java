package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum TeamEnum {

    STATUS_LOOKING_FOR_PROJECT("Looking for project"),
    STATUS_PROJECT_IN_PROGRESS("Project in progress"),
    STATUS_PROJECT_IS_FINISHED("Project is finished"),
    STARTER_TEAM_NAME("Build-in team");

    private String teamEnum;

    TeamEnum(String teamEnum) {
        this.teamEnum = teamEnum;
    }

    public String getTeamEnum() {
        return teamEnum;
    }

    public void setTeamEnum(String teamEnum) {
        this.teamEnum = teamEnum;
    }
}
