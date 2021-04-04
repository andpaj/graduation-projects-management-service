package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum ApplicationEnum {

    SENT("Application is sent"),
    ACCEPTED("Application is accepted by the supervisor"),
    DECLINED("Application is declined by the supervisor"),
    DECLINED_BY_STUDENT("Application is declined by the student"),
    PROJECT_IN_PROGRESS("Project in progress");

    private String applicationEnum;

    ApplicationEnum(String applicationEnum) {
        this.applicationEnum = applicationEnum;
    }

    public String getApplicationEnum() {
        return applicationEnum;
    }

    public void setApplicationEnum(String applicationEnum) {
        this.applicationEnum = applicationEnum;
    }
}
