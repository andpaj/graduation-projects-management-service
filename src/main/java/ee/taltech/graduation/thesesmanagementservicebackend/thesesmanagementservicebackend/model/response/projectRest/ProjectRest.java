package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.projectRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.applicationRest.ApplicationRestWithoutProject;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.tagRest.TagRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamRest.TeamRest;

import java.util.Date;
import java.util.List;

public class ProjectRest {

    private String projectId;
    private String userId;
    private String status;
    private Date creatingTime;
    private String language;
    private String title;
    private String description;
    private String degree;
    private int studentAmount;
    private TeamRest team;
    private List<TagRest> tags;
    private List<ApplicationRestWithoutProject> applications;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public List<TagRest> getTags() {
        return tags;
    }

    public void setTags(List<TagRest> tags) {
        this.tags = tags;
    }

    public List<ApplicationRestWithoutProject> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationRestWithoutProject> applications) {
        this.applications = applications;
    }

    public TeamRest getTeam() {
        return team;
    }

    public void setTeam(TeamRest team) {
        this.team = team;
    }
}
