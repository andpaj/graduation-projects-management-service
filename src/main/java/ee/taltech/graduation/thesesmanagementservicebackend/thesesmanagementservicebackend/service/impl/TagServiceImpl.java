package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.TagEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TagRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TagService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TagDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    Utils utils;

    @Override
    public TagDto getTagByTagId(String id) {
        ModelMapper modelMapper = new ModelMapper();
        TagEntity tagEntity = tagRepository.findByTagId(id);
        if (tagEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TAG.getErrorMessage());

        TagDto tagDto = modelMapper.map(tagEntity, TagDto.class);

        return tagDto;

    }

    @Override
    public List<TagDto> getAllTags() {
        ModelMapper modelMapper = new ModelMapper();
        List<TagDto> tagDtoList = new ArrayList<>();
        List<TagEntity> tagEntityList = tagRepository.findAll();
        for (TagEntity tagEntity: tagEntityList) {
            TagDto tagDto = modelMapper.map(tagEntity, TagDto.class);
            tagDtoList.add(tagDto);
        }
        return tagDtoList;


    }

    @Override
    public TagDto createTag(TagDto tagDto) {
        ModelMapper modelMapper = new ModelMapper();
        tagDto.setTagId(utils.generateTagId(30));
        TagEntity tagEntity = modelMapper.map(tagDto, TagEntity.class);
        TagEntity savedEntity = tagRepository.save(tagEntity);
        TagDto savedDto = modelMapper.map(savedEntity, TagDto.class);

        return savedDto;

    }

    @Override
    public void deleteTag(String id) {

        TagEntity tagEntity = tagRepository.findByTagId(id);
        if (tagEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_TAG.getErrorMessage());

        tagRepository.delete(tagEntity);



    }
}
