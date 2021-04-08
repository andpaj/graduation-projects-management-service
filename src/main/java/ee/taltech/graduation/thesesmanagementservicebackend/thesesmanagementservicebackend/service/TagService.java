package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TagDto;

import java.util.List;

public interface TagService {

    TagDto getTagByTagId(String tagId);
    List<TagDto> getAllTags();
    TagDto createTag(TagDto tagDto);
    void deleteTag(String tagId);
}
