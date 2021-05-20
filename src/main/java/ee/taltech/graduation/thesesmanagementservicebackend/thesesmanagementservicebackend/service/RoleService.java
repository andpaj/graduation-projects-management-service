package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto creatRole(RoleDto roleDto, List<String> authorities);
}
