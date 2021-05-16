package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String teamId;

    @Column
    private String teamName;

    @Column()
    private String status;

    @Column
    private String authorId;

    @OneToOne()
    @JoinColumn(name = "project")
    private ProjectEntity project;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "team",
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,CascadeType.REMOVE})
    private List<TeamMemberEntity> teamMembers = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "team",
            cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private List<ApplicationEntity> applications = new ArrayList<>();

    @PreRemove
    public void removeTeam() {
        if (project != null){
            project.setTeam(null);
        }



    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<TeamMemberEntity> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMemberEntity> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<ApplicationEntity> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationEntity> applications) {
        this.applications = applications;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
