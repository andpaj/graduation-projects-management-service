package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;

public interface UserService {

    UserDto getUserByUserId(String userId);
    UserDto createUser(UserDto user);
    UserDto updateUser(String userId, UserDto user);
    void deleteUser(String userId);



}
