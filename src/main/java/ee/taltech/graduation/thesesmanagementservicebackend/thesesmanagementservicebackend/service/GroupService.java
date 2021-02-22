package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.GroupDto;

import java.util.List;

public interface GroupService {

    GroupDto createGroup(GroupDto groupDto, String parentGroupId);
    GroupDto getGroupById(String id);
    List<GroupDto> getAllGroups();


}
