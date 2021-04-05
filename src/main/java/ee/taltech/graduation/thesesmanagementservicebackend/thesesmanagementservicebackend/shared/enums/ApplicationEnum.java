package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum ApplicationEnum {

    SENT("application_sent"),
    ACCEPTED("accept_supervisor"),
    DECLINED("decline_supervisor"),
    DECLINED_BY_STUDENT("decline_student"),
    PROJECT_IN_PROGRESS("application_confirmed");

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
