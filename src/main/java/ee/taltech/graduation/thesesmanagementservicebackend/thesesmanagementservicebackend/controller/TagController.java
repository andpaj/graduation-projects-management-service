package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.TagRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.tagRest.TagRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.TagService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.TagDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @GetMapping(path = "/{tagId}")
    public TagRest getTagByTagId(@PathVariable String tagId){
        ModelMapper modelMapper = new ModelMapper();
        TagDto tagDto = tagService.getTagByTagId(tagId);
        TagRest tagRest = modelMapper.map(tagDto, TagRest.class);

        return tagRest;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public TagRest createTag(@Valid @RequestBody TagRequestModel tagRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        TagDto tagDto = modelMapper.map(tagRequestModel, TagDto.class);
        TagDto savedTag = tagService.createTag(tagDto);
        TagRest tagRest = modelMapper.map(savedTag, TagRest.class);

        return tagRest;

    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Bearer JWT token", paramType = "header")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{tagId}")
    public String deleteTag(@PathVariable String tagId){

        tagService.deleteTag(tagId);

        return tagId;
    }








}
