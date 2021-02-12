package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserWithThesesDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserByUserId(String userId);
    List<UserWithThesesDto> getUsersWithThesesList();
    List<UserDto> getUsers();
    UserDto createUser(UserDto user, String depatment);
    UserDto updateUser(String userId, UserDto user);
    void deleteUser(String userId);
    UserDto getUser(String email);



}
