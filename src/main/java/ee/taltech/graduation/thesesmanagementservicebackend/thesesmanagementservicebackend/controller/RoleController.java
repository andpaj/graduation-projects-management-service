package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.AuthorityDetailsRequest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.RoleDetailsRequest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.RoleRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.RoleService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.AuthorityDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping()
    public RoleRest createRole(@RequestBody RoleDetailsRequest detailsRequest){

        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(detailsRequest, RoleDto.class);
        List<String> authorities = detailsRequest.getAuthorities();

        RoleDto savedRole = roleService.creatRole(roleDto, authorities);

        RoleRest roleRest = modelMapper.map(savedRole, RoleRest.class);

        return roleRest;



    }


}
