package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response;

public enum ErrorMessages {

    RECORD_ALREADY_EXISTS("Record already exists"),
    NO_RECORD_FOUND_USER("User with provided id is not found"),
    NO_RECORD_FOUND_USER_EMAIL("User with provided email is not found"),
    NO_RECORD_FOUND_APPLICATION("Application with provided id is not found"),
    NO_RECORD_FOUND_GROUP("Group with provided id is not found"),
    NO_RECORD_FOUND_PARENT_GROUP("Parent group with provided id is not found"),
    NO_RECORD_FOUND_PROJECT("Project with provided id is not found"),
    NO_RECORD_FOUND_TEAM("Team with provided id is not found"),
    NO_RECORD_FOUND_TAG("Tag with provided id is not found"),
    NO_RECORD_FOUND_TEAM_MEMBER("Team member with provided id is not found"),
    TEAM_MEMBER_ALREADY_IN_TEAM("This user is already in the team"),
    APPLICATION_ACCEPTED_DECLINED_BY_SUPERVISOR("This application is already accepted or declined by supervisor"),
    APPLICATION_CANT_DECLINED_BY_STUDENT("This application cant be decline by student"),
    APPLICATION_CANT_CONFIRMED_BY_STUDENT("This application cant be confirmed by student"),
    APPLICATION_NO_RIGHTS("No authorities for user with this id"),
    APPLICATION_CANT_BE_SEND("Application cant be send because all team members must accept team membership"),
    PROJECT_IS_NOT_AVAILABLE("This project is not available"),
    MEMBER_ALREADY_ACCEPTED("This team member is already accepted"),
    APPLICATION_IS_ALREADY_SENT("Application to this project has already sent"),
    TAG_WITH_THAT_NAME_IS_ALREADY_CREATED("Tag with such name has already created"),
    STUDENT_AMOUNT_HIGH("This project is not suitable for such amount of team members");


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
