package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.GroupDto;

import java.util.List;

public interface GroupService {

    GroupDto createGroup(GroupDto groupDto, String parentGroupId);
    GroupDto getGroupById(String groupId);
    List<GroupDto> getAllGroups();
    void deleteGroup(String groupId);
    GroupDto getGroupOnlyWithSupervisors(String groupId);
    GroupDto getGroupOnlyWithStudents(String groupId);
}
