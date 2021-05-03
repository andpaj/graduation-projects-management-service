package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import java.util.List;
import java.util.Set;

public class ProjectDetailsRequestModel {

    private String language;
    private String title;
    private String description;
    private int studentAmount;
    private String degree;
    private Set<TagRequestModel> tags;
    private List<String> groups;

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

    public Set<TagRequestModel> getTags() {
        return tags;
    }

    public void setTags(Set<TagRequestModel> tags) {
        this.tags = tags;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
