package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class ProjectDetailsRequestModel {

    @NotNull(message = "language cannot be null")
    private String language;
    @NotNull(message = "title cannot be null")
    private String title;
    @NotNull(message = "description cannot be null")
    private String description;
    @NotNull(message = "student amount cannot be null")
    private int studentAmount;
    @NotNull(message = "degree cannot be null")
    private String degree;
    private Set<TagRequestModel> tags;
    private List<String> groups;
    private List<String> coSupervisors;

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

    public List<String> getCoSupervisors() {
        return coSupervisors;
    }

    public void setCoSupervisors(List<String> coSupervisors) {
        this.coSupervisors = coSupervisors;
    }
}
