package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.AuthorityDetailsRequest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.AuthorityRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.AuthorityService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.AuthorityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;


    @PostMapping()
    public AuthorityDto createAuthority(@RequestBody AuthorityDetailsRequest detailsRequest){

        ModelMapper modelMapper = new ModelMapper();
        AuthorityDto authorityDto = modelMapper.map(detailsRequest, AuthorityDto.class);

        AuthorityDto savedAuthority = authorityService.createAuthority(authorityDto);

        //uthorityRest authorityRest = modelMapper.map(savedAuthority, AuthorityRest.class);

        return savedAuthority;



    }


}
