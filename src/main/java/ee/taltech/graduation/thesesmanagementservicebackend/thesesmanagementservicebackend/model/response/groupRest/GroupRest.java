package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.groupRest;

import java.util.List;

public class GroupRest {

    private String groupId;
    private String groupClass;
    private String groupName;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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


}
