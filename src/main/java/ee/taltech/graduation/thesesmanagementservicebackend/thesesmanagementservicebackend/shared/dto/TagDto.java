package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto;

import java.util.List;
import java.util.Set;

public class TagDto {

    private long id;
    private String tagId;
    private String tagName;
    private Set<ThesisDto> thesis;

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

    public Set<ThesisDto> getThesis() {
        return thesis;
    }

    public void setThesis(Set<ThesisDto> thesis) {
        this.thesis = thesis;
    }
}
