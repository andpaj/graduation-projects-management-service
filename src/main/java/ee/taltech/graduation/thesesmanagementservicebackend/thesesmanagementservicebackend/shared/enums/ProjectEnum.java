package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum ProjectEnum {

    STATUS_AVAILABLE("project_available"),
    STATUS_NOT_AVAILABLE("project_not_available"),
    LANGUAGE_ENGLISH("english"),
    LANGUAGE_ESTONIAN("estonian"),
    DEGREE_BACHELOR("bachelor"),
    DEGREE_MASTER("master");

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
