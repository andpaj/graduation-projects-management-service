package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;

import java.util.List;

public class GroupDto {

    private long id;
    private String groupId;
    private String groupStatus;
    private String groupClass;
    private String groupName;
    private List<GroupDto> subGroups;
    private GroupDto parentGroup;
    private List<UserDto> users;
    private List<ProjectDto> projects;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
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

    public List<GroupDto> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<GroupDto> subGroups) {
        this.subGroups = subGroups;
    }

    public GroupDto getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(GroupDto parentGroup) {
        this.parentGroup = parentGroup;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}
