package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "thesis")
public class ThesisEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String thesisId;

    @Column(nullable = false)
    private String teacherId;

    @Column()
    private String applicationId;  //one student or group of students(applicationTeam class)

    @Column(nullable = false)
    private String status;  // change for enum

    @Column(nullable = false)
    private Date creatingTime;

    @Column
    private Date acceptingTime;

    @Column(nullable = false)
    private String language; // change String for Enum

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int studentAmount;

    @Column(nullable = false)
    private String degree; // change for enum

    @Column(nullable = false)
    private int difficultyRating; //change for enum

    public ThesisEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThesisId() {
        return thesisId;
    }

    public void setThesisId(String thesisId) {
        this.thesisId = thesisId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

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

    public Date getAcceptingTime() {
        return acceptingTime;
    }

    public void setAcceptingTime(Date acceptingTime) {
        this.acceptingTime = acceptingTime;
    }
}
