package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.applicationRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.projectRest.ProjectRest;

import java.util.Date;

public class ApplicationRestWithoutTeam {

    private String applicationId;
    private String status;
    private Date creatingTime;
    private String message;
    private ProjectRest project;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProjectRest getProject() {
        return project;
    }

    public void setProject(ProjectRest project) {
        this.project = project;
    }
}
