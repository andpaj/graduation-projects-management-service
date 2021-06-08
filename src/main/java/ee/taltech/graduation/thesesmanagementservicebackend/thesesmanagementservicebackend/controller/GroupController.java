package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.GroupDetailsRequest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.groupRest.GroupRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.groupRest.GroupRestWithSubGroups;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.groupRest.GroupRestWithoutUsers;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.GroupService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.GroupDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    GroupService groupService;


    @GetMapping(path = "/{groupId}")
    public GroupRestWithSubGroups getGroupById(@PathVariable String groupId){

        ModelMapper modelMapper = new ModelMapper();

        GroupDto groupDto = groupService.getGroupById(groupId);

        GroupRestWithSubGroups returnValue = modelMapper.map(groupDto, GroupRestWithSubGroups.class);

        return returnValue;

    }

    @GetMapping(path = "/groupOnlyWithSupervisors/{groupId}")
    public GroupRestWithSubGroups getGroupOnlyWithSupervisors(@PathVariable String groupId){

        ModelMapper modelMapper = new ModelMapper();

        GroupDto groupDto = groupService.getGroupOnlyWithSupervisors(groupId);

        GroupRestWithSubGroups returnValue = modelMapper.map(groupDto, GroupRestWithSubGroups.class);

        return returnValue;


    }

    @GetMapping(path = "/groupOnlyWithStudents/{groupId}")
    public GroupRestWithSubGroups getGroupOnlyWithStudents(@PathVariable String groupId){

        ModelMapper modelMapper = new ModelMapper();

        GroupDto groupDto = groupService.getGroupOnlyWithStudents(groupId);

        GroupRestWithSubGroups returnValue = modelMapper.map(groupDto, GroupRestWithSubGroups.class);

        return returnValue;

    }

    @GetMapping(path = "/getAllGroups")
    public List<GroupRestWithoutUsers> getAllGroups(){

        ModelMapper modelMapper = new ModelMapper();
        List<GroupRestWithoutUsers> groupsList = new ArrayList<>();

        List<GroupDto> groupDtoList = groupService.getAllGroups();

        for (GroupDto groupDto: groupDtoList){

            GroupRestWithoutUsers group = modelMapper.map(groupDto, GroupRestWithoutUsers.class);
            groupsList.add(group);
        }


        return groupsList;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public GroupRest createGroup(@Valid @RequestBody GroupDetailsRequest groupDetailsRequest){

        ModelMapper modelMapper = new ModelMapper();
        String parentGroupId = groupDetailsRequest.getParentTest();

        GroupDto groupDto = modelMapper.map(groupDetailsRequest, GroupDto.class);
        GroupDto savedGroup = groupService.createGroup(groupDto, parentGroupId);

        GroupRest returnGroup = modelMapper.map(savedGroup, GroupRest.class);

        return returnGroup;

    }



    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{groupId}")
    public String deleteGroup(@PathVariable String groupId){

        groupService.deleteGroup(groupId);

        return groupId;
    }










}
