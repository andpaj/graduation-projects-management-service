package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserWithProjectsDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto getUserByUserId(String userId);
    UserDto getUserByEmail(String email);
    List<UserWithProjectsDto> getUsersWithProjectList();
    List<UserDto> getUsers();
    UserDto createUser(UserDto user, String depatment);
    UserDto updateUser(String userId, UserDto user);
    void deleteUser(String userId);
    UserDto getUser(String email);



}
