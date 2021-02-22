package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.UserDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.UserRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.UserWithProjectsRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.UserService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public UserWithProjectsRest getUser(@PathVariable String id){

        UserDto userDto = userService.getUserByUserId(id);
        ModelMapper modelMapper = new ModelMapper();
        UserWithProjectsRest returnValue = modelMapper.map(userDto, UserWithProjectsRest.class);

        return  returnValue;

    }

    @GetMapping()
    public List<UserWithProjectsRest> getUsers(){
        ModelMapper modelMapper = new ModelMapper();
        List<UserWithProjectsRest> allUsers = new ArrayList<>();
        List<UserDto> usersDto = userService.getUsers();

        for (UserDto userDto : usersDto){
            UserWithProjectsRest userRest = modelMapper.map(userDto, UserWithProjectsRest.class);
            allUsers.add(userRest);
        }

        return allUsers;
    }

    //get all users with thesis

    @GetMapping(path = "/withProjects")
    public List<UserWithProjectsRest> getUsersWithProjects(){
        ModelMapper modelMapper = new ModelMapper();
        List<UserWithProjectsRest> allUsers = new ArrayList<>();
        List<UserDto> usersDto = userService.getUsersWithProjectList();

        for (UserDto userDto : usersDto){

            UserWithProjectsRest userRest = modelMapper.map(userDto, UserWithProjectsRest.class);
            allUsers.add(userRest);

        }

        return allUsers;

    }

    @PostMapping()
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        String department = userDetails.getDepartmentName();
        String group = userDetails.getGroup();

        UserDto createdUser = userService.createUser(userDto, department, group);
        UserRest returnValue = modelMapper.map(createdUser, UserRest.class);

        return returnValue;
    }

    @PutMapping(path = "/{id}")
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails){


        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto updatedUser = userService.updateUser(id, userDto);

        UserRest returnValue = modelMapper.map(updatedUser, UserRest.class);

        return returnValue;

    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id){

        userService.deleteUser(id);

        return id;
    }







}
