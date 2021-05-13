package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDetailsRequestModel {
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last cannot be null")
    private String lastName;
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Graduation level cannot be null")
    private String graduationLevel;
    @NotNull(message = "Groups cannot be null")
    private List<String> groups;


    public UserDetailsRequestModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getGraduationLevel() {
        return graduationLevel;
    }

    public void setGraduationLevel(String graduationLevel) {
        this.graduationLevel = graduationLevel;
    }
}
