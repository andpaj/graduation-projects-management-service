package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserGroupRoleEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.GroupService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.GroupDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    Utils utils;

    @Override
    public GroupDto getGroupById(String groupId) {

        ModelMapper modelMapper = new ModelMapper();

        GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
        if (groupEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());

        GroupDto groupDto = modelMapper.map(groupEntity, GroupDto.class);

        return groupDto;

    }

    @Override
    public GroupDto getGroupOnlyWithStudents(String groupId) {
        ModelMapper modelMapper = new ModelMapper();
        GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
        if (groupEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());

        List<UserEntity> supervisors = new ArrayList<>();

        for (UserGroupRoleEntity userGroupRoleEntity: groupEntity.getUserGroupRole()){
            if (userGroupRoleEntity.getRole().getName().equals(Roles.ROLE_STUDENT.name())){
                supervisors.add(userGroupRoleEntity.getUser());
            }
        }

        GroupDto groupDto = modelMapper.map(groupEntity, GroupDto.class);

        List<UserDto> supervisorsDto = new ArrayList<>();
        for (UserEntity userEntity: supervisors){
            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            supervisorsDto.add(userDto);
        }

        groupDto.setUsers(supervisorsDto);

        return groupDto;

    }

    //по груп айди выдать все проекты с этим груп айди


    @Override
    public GroupDto getGroupOnlyWithSupervisors(String groupId) {

        ModelMapper modelMapper = new ModelMapper();
        GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
        if (groupEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());

        List<UserEntity> supervisors = new ArrayList<>();

        for (UserGroupRoleEntity userGroupRoleEntity: groupEntity.getUserGroupRole()){
            if (userGroupRoleEntity.getRole().getName().equals(Roles.ROLE_TEACHER.name())){
                supervisors.add(userGroupRoleEntity.getUser());
            }
        }

        GroupDto groupDto = modelMapper.map(groupEntity, GroupDto.class);

        List<UserDto> supervisorsDto = new ArrayList<>();
        for (UserEntity userEntity: supervisors){
            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            supervisorsDto.add(userDto);
        }

        groupDto.setUsers(supervisorsDto);

        return groupDto;

    }

    @Override
    public List<GroupDto> getAllGroups() {

        ModelMapper modelMapper = new ModelMapper();
        List<GroupEntity> groupEntityList = groupRepository.findAll();
        List<GroupDto> groupDtoList = new ArrayList<>();

        for (GroupEntity groupEntity: groupEntityList){

            GroupDto groupDto = modelMapper.map(groupEntity, GroupDto.class);
            groupDtoList.add(groupDto);

        }

        return groupDtoList;

    }


    @Override
    public GroupDto createGroup(GroupDto groupDto, String parentGroupId) {
        ModelMapper modelMapper = new ModelMapper();
        groupDto.setGroupId(utils.generateGroupId(30));

        GroupEntity groupEntity = modelMapper.map(groupDto, GroupEntity.class);
        GroupEntity parentGroupEntity = groupRepository.findByGroupId(parentGroupId);

        if (parentGroupEntity == null && groupEntity.getParentGroup() != null)
            throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_PARENT_GROUP.getErrorMessage());

        if (parentGroupEntity != null) {
            groupEntity.setParentGroup(parentGroupEntity);
            parentGroupEntity.getSubGroups().add(groupEntity);
        }

        GroupEntity savedGroup = groupRepository.save(groupEntity);
        GroupDto returnValue = modelMapper.map(savedGroup, GroupDto.class);

        return returnValue;

    }


    @Override
    public void deleteGroup(String groupId) {
        GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
        if (groupEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());

        groupRepository.delete(groupEntity);
    }

}
