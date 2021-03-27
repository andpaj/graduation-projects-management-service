package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;


import java.util.Date;

public class ApplicationRest {

    private String applicationId;
    private String status;
    private Date creatingTime;
    private String message;
    private TeamRest team;
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


    public TeamRest getTeam() {
        return team;
    }

    public void setTeam(TeamRest team) {
        this.team = team;
    }

    public ProjectRest getProject() {
        return project;
    }

    public void setProject(ProjectRest project) {
        this.project = project;
    }
}
