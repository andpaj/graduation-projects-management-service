package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String groupId;

    private String groupStatus;

    private String groupClass;

    private String groupName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    List<GroupEntity> subGroups;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_group")
    GroupEntity parentGroup;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "groupEntities")
    //@OneToMany(mappedBy = "groupEntity",fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "groupEntity")
    private List<UserGroupRoleEntity> userGroupRole;


    public GroupEntity() {
    }

    @PreRemove
    public void removeGroup() {
        for (UserEntity user: users){
            user.getGroupEntities().remove(this);
        }

        if (parentGroup != null) {
            parentGroup.getSubGroups().remove(this);
        }
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

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
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

    public List<UserGroupRoleEntity> getUserGroupRole() {
        return userGroupRole;
    }

    public void setUserGroupRole(List<UserGroupRoleEntity> userGroupRole) {
        this.userGroupRole = userGroupRole;
    }
}
