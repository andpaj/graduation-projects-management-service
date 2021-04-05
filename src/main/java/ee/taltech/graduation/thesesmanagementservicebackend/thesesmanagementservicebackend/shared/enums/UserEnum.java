package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum UserEnum {

    STATUS_ACTIVE("active"),
    STATUS_NOT_ACTIVE("not_active"),
    GRADUATION_LEVEL_BACHELOR("bachelor"),
    GRADUATION_LEVEL_MASTER("master");

    private String userEnum;

    UserEnum(String userEnum) {
        this.userEnum = userEnum;
    }

    public String getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(String userEnum) {
        this.userEnum = userEnum;
    }
}
