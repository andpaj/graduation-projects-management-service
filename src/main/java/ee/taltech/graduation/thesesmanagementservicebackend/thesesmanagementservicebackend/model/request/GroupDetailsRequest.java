package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;



public class GroupDetailsRequest {

    private String groupClass;
    private String groupName;
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
