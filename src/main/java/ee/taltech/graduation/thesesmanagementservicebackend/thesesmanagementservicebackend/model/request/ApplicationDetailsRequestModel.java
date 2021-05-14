package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ApplicationDetailsRequestModel {

    @NotNull(message = "work type cannot be null")
    private String workType;
    private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
