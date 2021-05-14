package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;

import javax.validation.constraints.NotNull;

public class UserLoginRequestModel {
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
