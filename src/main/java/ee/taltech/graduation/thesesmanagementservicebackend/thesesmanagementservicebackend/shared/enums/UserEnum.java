package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums;

public enum UserEnum {

    STATUS_ACTIVE("Active"),
    STATUS_NOT_ACTIVE("Not active"),
    GRADUATION_LEVEL_BACHELOR("Bachelor"),
    GRADUATION_LEVEL_MASTER("Master");

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
