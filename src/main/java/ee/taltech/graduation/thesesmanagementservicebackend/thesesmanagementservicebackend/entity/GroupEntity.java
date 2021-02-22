package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue
    private long id;

    private String groupId;

    private String groupClass;

    private String groupName;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    List<GroupEntity> subGroups;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_group")
    GroupEntity parentGroup;

    @OneToMany(mappedBy = "groupEntity",fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private List<UserEntity> users = new ArrayList<>();


    public GroupEntity() {
    }

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

    public List<GroupEntity> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<GroupEntity> subGroups) {
        this.subGroups = subGroups;
    }

    public GroupEntity getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(GroupEntity parentGroup) {
        this.parentGroup = parentGroup;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
