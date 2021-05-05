package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.projectRest;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.groupRest.GroupRestWithoutUsers;

import java.util.List;

public class ProjectRestShort {

    private String projectId;
    private String userId;
    private String status;
    private String title;
    private List<GroupRestWithoutUsers> groupEntities;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GroupRestWithoutUsers> getGroupEntities() {
        return groupEntities;
    }

    public void setGroupEntities(List<GroupRestWithoutUsers> groupEntities) {
        this.groupEntities = groupEntities;
    }
}
