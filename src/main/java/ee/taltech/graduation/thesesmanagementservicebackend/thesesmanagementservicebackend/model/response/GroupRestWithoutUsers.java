package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import java.util.List;

public class GroupRestWithoutUsers {

    private String groupId;
    private String groupClass;
    private String groupName;
    private List<GroupRestWithoutUsers> subGroups;

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

    public List<GroupRestWithoutUsers> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<GroupRestWithoutUsers> subGroups) {
        this.subGroups = subGroups;
    }
}
