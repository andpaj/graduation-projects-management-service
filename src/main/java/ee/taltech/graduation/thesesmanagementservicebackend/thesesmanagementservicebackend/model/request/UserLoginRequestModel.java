package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

public class UserLoginRequestModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
