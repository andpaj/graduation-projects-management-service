package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import java.util.List;
import java.util.Set;

public class ThesisDetailsRequestModel {

    //teacher
    private String language;
    private String title;
    private String description;
    private int studentAmount;
    private String degree;
    private int difficultyRating;
    private Set<TagRequestModel> tags;

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

    public int getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public Set<TagRequestModel> getTags() {
        return tags;
    }

    public void setTags(Set<TagRequestModel> tags) {
        this.tags = tags;
    }
}
