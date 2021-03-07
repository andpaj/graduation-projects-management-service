package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.TagRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.TagRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TagService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TagDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping(path = "/{id}")
    public TagRest getTagByTagId(@PathVariable String id){
        ModelMapper modelMapper = new ModelMapper();
        TagDto tagDto = tagService.getTagByTagId(id);
        TagRest tagRest = modelMapper.map(tagDto, TagRest.class);

        return tagRest;

    }

    @GetMapping(path = "/allTags")
    public List<TagRest> getAllTags(){
        ModelMapper modelMapper = new ModelMapper();
        List<TagRest> returnValue = new ArrayList<>();
        List<TagDto> tagDtos = tagService.getAllTags();
        for (TagDto tagDto: tagDtos){

            TagRest tagRest = modelMapper.map(tagDto, TagRest.class);
            returnValue.add(tagRest);
        }

        return returnValue;


    }

    @PostMapping(path = "/create")
    public TagRest createTag(@RequestBody TagRequestModel tagRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        TagDto tagDto = modelMapper.map(tagRequestModel, TagDto.class);
        TagDto savedTag = tagService.createTag(tagDto);
        TagRest tagRest = modelMapper.map(savedTag, TagRest.class);

        return tagRest;

    }


    @DeleteMapping(path = "/delete/{id}")
    public String deleteTag(@PathVariable String id){

        tagService.deleteTag(id);

        return "Tag with id " + id + " was deleted";
    }








}
