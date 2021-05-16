package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.UserDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.userRest.UserRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.userRest.UserWithProjectsRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.UserService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{userId}")
    public UserRest getUser(@PathVariable String userId){

        UserDto userDto = userService.getUserByUserId(userId);
        ModelMapper modelMapper = new ModelMapper();
        UserRest returnValue = modelMapper.map(userDto, UserRest.class);

        return  returnValue;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/getSupervisors")
    public List<UserWithProjectsRest> getSupervisors(){
        ModelMapper modelMapper = new ModelMapper();
        List<UserWithProjectsRest> allSupervisors = new ArrayList<>();
        List<UserDto> supervisorsDto = userService.getSupervisors();

        for (UserDto userDto : supervisorsDto){
            UserWithProjectsRest userRest = modelMapper.map(userDto, UserWithProjectsRest.class);
            allSupervisors.add(userRest);
        }

        return allSupervisors;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/getStudents")
    public List<UserWithProjectsRest> getStudents(){
        ModelMapper modelMapper = new ModelMapper();
        List<UserWithProjectsRest> allStudents = new ArrayList<>();
        List<UserDto> studentsDto = userService.getStudents();

        for (UserDto userDto : studentsDto){
            UserWithProjectsRest userRest = modelMapper.map(userDto, UserWithProjectsRest.class);
            allStudents.add(userRest);
        }

        return allStudents;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
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

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public UserRest createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        List<String> groups = userDetails.getGroups();

        UserDto createdUser = userService.createUser(userDto, groups);
        UserRest returnValue = modelMapper.map(createdUser, UserRest.class);

        return returnValue;
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
    @PutMapping(path = "/{userId}")
    public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userDetails){


        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto updatedUser = userService.updateUser(userId, userDto);

        UserRest returnValue = modelMapper.map(updatedUser, UserRest.class);

        return returnValue;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);

        return userId;
    }

}
