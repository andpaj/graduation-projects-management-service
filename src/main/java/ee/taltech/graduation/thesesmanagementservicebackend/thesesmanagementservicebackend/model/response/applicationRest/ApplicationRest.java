package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.applicationRest;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.projectRest.ProjectRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamRest.TeamForApplicationsRest;

import java.util.Date;

public class ApplicationRest {

    private String applicationId;
    private String status;
    private Date creatingTime;
    private String message;
    private TeamForApplicationsRest team;
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

    public TeamForApplicationsRest getTeam() {
        return team;
    }

    public void setTeam(TeamForApplicationsRest team) {
        this.team = team;
    }

    public ProjectRest getProject() {
        return project;
    }

    public void setProject(ProjectRest project) {
        this.project = project;
    }
}
