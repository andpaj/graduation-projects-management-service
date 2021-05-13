package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;


import javax.validation.constraints.NotNull;

public class GroupDetailsRequest {

    @NotNull(message = "group class cannot be null")
    private String groupClass;
    @NotNull(message = "group name cannot be null")
    private String groupName;
    @NotNull(message = "parent group cannot be null")
    private String parentTest;


    public String getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(String groupClass) {
        this.groupClass = groupClass;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParentTest() {
        return parentTest;
    }

    public void setParentTest(String parentTest) {
        this.parentTest = parentTest;
    }
}
