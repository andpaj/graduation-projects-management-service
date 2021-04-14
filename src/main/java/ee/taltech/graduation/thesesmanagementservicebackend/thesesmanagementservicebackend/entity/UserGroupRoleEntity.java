package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.Roles;

import javax.management.relation.Role;
import javax.persistence.*;

@Entity
@Table(name = "user_group_role")
public class UserGroupRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    //@MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    //@MapsId("groupId")
    @JoinColumn(name = "group_id")
    private GroupEntity groupEntity;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
