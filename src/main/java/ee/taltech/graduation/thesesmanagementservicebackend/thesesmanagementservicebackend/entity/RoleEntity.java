package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "role",
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private List<UserGroupRoleEntity> userGroupRole;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "roles_authorities",
            joinColumns =@JoinColumn(name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "authorities_id", referencedColumnName = "id"))
    private List<AuthorityEntity> authorities;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public List<UserGroupRoleEntity> getUserGroupRole() {
        return userGroupRole;
    }

    public void setUserGroupRole(List<UserGroupRoleEntity> userGroupRole) {
        this.userGroupRole = userGroupRole;
    }
}
