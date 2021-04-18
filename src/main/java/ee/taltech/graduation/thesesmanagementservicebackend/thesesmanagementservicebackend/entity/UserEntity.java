package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column()
    private String graduationLevel;

    @Column()
    private String starterTeam;

    @Column
    private String confirmedProject;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private Set<ProjectEntity> projects = new HashSet<>();


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
            })
    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<GroupEntity> groupEntities;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user",
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private List<TeamMemberEntity> teamMembers = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST
            })
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "user")
    private List<UserGroupRoleEntity> userGroupRole;

    @PreRemove
    public void removeUser() {
       for (GroupEntity groupEntity: groupEntities) {
            groupEntity.getUsers().remove(this);
       }

       for (RoleEntity roleEntity: roles){
           roleEntity.getUsers().remove(this);
       }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }

    public String getGraduationLevel() {
        return graduationLevel;
    }

    public void setGraduationLevel(String graduationLevel) {
        this.graduationLevel = graduationLevel;
    }

    public List<GroupEntity> getGroupEntities() {
        return groupEntities;
    }

    public void setGroupEntities(List<GroupEntity> groupEntities) {
        this.groupEntities = groupEntities;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<TeamMemberEntity> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMemberEntity> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getStarterTeam() {
        return starterTeam;
    }

    public void setStarterTeam(String starterTeam) {
        this.starterTeam = starterTeam;
    }

    public String getConfirmedProject() {
        return confirmedProject;
    }

    public void setConfirmedProject(String confirmedProject) {
        this.confirmedProject = confirmedProject;
    }

    public List<UserGroupRoleEntity> getUserGroupRole() {
        return userGroupRole;
    }

    public void setUserGroupRole(List<UserGroupRoleEntity> userGroupRole) {
        this.userGroupRole = userGroupRole;
    }
}
