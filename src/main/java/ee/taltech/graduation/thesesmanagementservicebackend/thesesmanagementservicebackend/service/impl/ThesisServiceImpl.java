package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.ThesisEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.ThesisRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ThesisService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    ThesisRepository thesisRepository;


    @Override
    public ThesisDto getThesisByThesisId(String thesisId) {

        ThesisEntity thesisEntity = thesisRepository.findByThesisId(thesisId);

        //check null

        ModelMapper modelMapper  = new ModelMapper();

        ThesisDto thesisDto = modelMapper.map(thesisEntity, ThesisDto.class);

        return thesisDto;



    }

    @Override
    public ThesisDto createThesis(ThesisDto thesisDto) {

        ModelMapper modelMapper = new ModelMapper();
        ThesisEntity thesisEntity = modelMapper.map(thesisDto, ThesisEntity.class);


        ThesisEntity storedThesis = thesisRepository.save(thesisEntity);

        ThesisDto returnValue = modelMapper.map(storedThesis, ThesisDto.class);
        return returnValue;

    }
}
