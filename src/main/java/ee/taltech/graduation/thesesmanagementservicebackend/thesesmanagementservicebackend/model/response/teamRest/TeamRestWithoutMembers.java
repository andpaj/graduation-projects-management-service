package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.teamRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.applicationRest.ApplicationRestWithoutProject;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.applicationRest.ApplicationRestWithoutTeam;

import java.util.List;

public class TeamRestWithoutMembers {

    private String teamId;
    private String teamName;
    private String status;
    private String authorId;
    private List<ApplicationRestWithoutProject> applications;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public List<ApplicationRestWithoutProject> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationRestWithoutProject> applications) {
        this.applications = applications;
    }
}
