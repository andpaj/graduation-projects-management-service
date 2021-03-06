package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProjectDto {

    private long id;
    private String projectId;
    private String status;  // change for enum
    private Date creatingTime;
    private Date acceptingTime;
    private String language; // change String for Enum
    private String title;
    private String description;
    private int studentAmount;
    private String degree; // change for enum
    private UserDto user;
    private Set<TagDto> tags;
    private TeamDto team;
    private List<ApplicationDto> applications;
    private List<GroupDto> groupEntities;
    private List<UserDto> coSupervisors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(Date creatingTime) {
        this.creatingTime = creatingTime;
    }

    public Date getAcceptingTime() {
        return acceptingTime;
    }

    public void setAcceptingTime(Date acceptingTime) {
        this.acceptingTime = acceptingTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

        public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public List<ApplicationDto> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationDto> applications) {
        this.applications = applications;
    }

    public List<GroupDto> getGroupEntities() {
        return groupEntities;
    }

    public void setGroupEntities(List<GroupDto> groupEntities) {
        this.groupEntities = groupEntities;
    }

    public List<UserDto> getCoSupervisors() {
        return coSupervisors;
    }

    public void setCoSupervisors(List<UserDto> coSupervisors) {
        this.coSupervisors = coSupervisors;
    }
}
