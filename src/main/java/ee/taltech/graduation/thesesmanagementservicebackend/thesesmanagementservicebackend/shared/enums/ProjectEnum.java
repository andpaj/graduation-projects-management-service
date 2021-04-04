package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum ProjectEnum {

    STATUS_AVAILABLE("Project is available"),
    STATUS_NOT_AVAILABLE("Project is not available"),
    LANGUAGE_ENGLISH("English"),
    LANGUAGE_ESTONIAN("Estonian"),
    DEGREE_BACHELOR("Bachelor"),
    DEGREE_MASTER("Master"),
    DIFFICULTY_EASY("Easy"),
    DIFFICULTY_MEDIUM("Medium"),
    DIFFICULTY_HARD("Hard");

    private String projectEnum;

    ProjectEnum(String projectEnum) {
        this.projectEnum = projectEnum;
    }

    public String getProjectEnum() {
        return projectEnum;
    }

    public void setProjectEnum(String projectEnum) {
        this.projectEnum = projectEnum;
    }
}
