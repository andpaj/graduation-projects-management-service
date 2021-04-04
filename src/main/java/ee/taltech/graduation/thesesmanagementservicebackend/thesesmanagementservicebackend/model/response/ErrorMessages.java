package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    NO_RECORD_FOUND_USER("User with provided id is not found"),
    NO_RECORD_FOUND_USER_EMAIL("User with provided email is not found"),
    NO_RECORD_FOUND_APPLICATION("Application with provided id is not found"),
    NO_RECORD_FOUND_GROUP("Group with provided id is not found"),
    NO_RECORD_FOUND_PARENT_GROUP("Parent group with provided id is not found"),
    NO_RECORD_FOUND_PROJECT("Project with provided id is not found"),
    NO_RECORD_FOUND_TEAM("Team with provided id is not found"),
    NO_RECORD_FOUND_TAG("Tag with provided id is not found"),
    NO_RECORD_FOUND_TEAM_MEMBER("Team member with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record");


    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
