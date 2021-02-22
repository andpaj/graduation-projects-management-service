package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

import java.util.List;

public class GroupRestWithSubGroups {

    private String groupId;
    private String groupClass;
    private String groupName;
    private List<GroupRestWithSubGroups> subGroups;
    private List<UserRest> users;

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

    public List<GroupRestWithSubGroups> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<GroupRestWithSubGroups> subGroups) {
        this.subGroups = subGroups;
    }

    public List<UserRest> getUsers() {
        return users;
    }

    public void setUsers(List<UserRest> users) {
        this.users = users;
    }
}
