package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import javax.validation.constraints.NotNull;

public class TagRequestModel {
    @NotNull(message = "Tag name cannot be null")
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
