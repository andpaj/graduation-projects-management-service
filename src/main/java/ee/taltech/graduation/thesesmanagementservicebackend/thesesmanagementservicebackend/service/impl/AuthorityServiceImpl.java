package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.AuthorityEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.AuthorityRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.AuthorityService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.AuthorityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;


    @Override
    public AuthorityDto createAuthority(AuthorityDto authorityDto) {

        if (authorityRepository.findByName(authorityDto.getName())!= null) throw
                new ServiceException("authority with this name is exist");

        ModelMapper modelMapper = new ModelMapper();

        AuthorityEntity authorityEntity = modelMapper.map(authorityDto, AuthorityEntity.class);

        AuthorityEntity savedAuthority = authorityRepository.save(authorityEntity);

        AuthorityDto returnValue = modelMapper.map(savedAuthority, AuthorityDto.class);

        return returnValue;


    }
}
