package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.AuthorityEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.RoleEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.AuthorityRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.RoleRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.RoleService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public RoleDto creatRole(RoleDto roleDto, List<String> authorities) {

        if (roleRepository.findByName(roleDto.getName()) != null) throw
            new ServiceException("The role with this name has already existed");

        List<AuthorityEntity> authorityEntities = new ArrayList<>();

        for (String authority: authorities ){

            AuthorityEntity authorityEntity = authorityRepository.findByName(authority);
            if (authorityEntity == null) throw
                new ServiceException("There is no authority with this name");
            authorityEntities.add(authorityEntity);
        }

        ModelMapper modelMapper = new ModelMapper();

        RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
        roleEntity.setAuthorities(authorityEntities);

        RoleEntity savedRole = roleRepository.save(roleEntity);

        RoleDto returnValue = modelMapper.map(savedRole, RoleDto.class);

        return returnValue;

    }
}
