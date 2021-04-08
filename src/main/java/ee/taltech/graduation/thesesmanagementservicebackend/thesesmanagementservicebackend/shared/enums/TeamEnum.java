package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum TeamEnum {

    //Команда есть но все участинкии не потвердили свое участие
    STATUS_NOT_ACTIVE("not_active"),
    STATUS_ACTIVE("active"),
    STATUS_PROJECT_IN_PROGRESS("in_progress"),
    STATUS_PROJECT_IS_FINISHED("is_finished"),
    STARTER_TEAM_NAME("personal_team");

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
