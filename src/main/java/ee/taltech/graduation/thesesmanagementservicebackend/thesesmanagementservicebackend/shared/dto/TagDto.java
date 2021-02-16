package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;

import java.util.Set;

public class TagDto {

    private long id;
    private String tagId;
    private String tagName;
//    private Set<ProjectDto> projects;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
//
//    public Set<ProjectDto> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(Set<ProjectDto> projects) {
//        this.projects = projects;
//    }
}
