package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto getUserByUserId(String userId);
    UserDto getUserByEmail(String email);
    List<UserDto> getUsersWithProjectList();
    List<UserDto> getUsers();
    UserDto createUser(UserDto userDto, List<String> groups, List<String> roles);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String userId);
    List<UserDto> getSupervisors();
    List<UserDto> getStudents();
    List<UserDto> getStudentsWithoutConfirmedProjects();
}
