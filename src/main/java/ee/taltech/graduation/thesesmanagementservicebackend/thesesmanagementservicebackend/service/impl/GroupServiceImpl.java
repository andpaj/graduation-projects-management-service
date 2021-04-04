package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.GroupService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.GroupDto;
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
    public GroupDto getGroupById(String id) {

        ModelMapper modelMapper = new ModelMapper();

        GroupEntity groupEntity = groupRepository.findByGroupId(id);
        if (groupEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());

        GroupDto groupDto = modelMapper.map(groupEntity, GroupDto.class);

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
    public void deleteGroup(String groupId) {
        GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
        if (groupEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());

        groupRepository.delete(groupEntity);
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


}
