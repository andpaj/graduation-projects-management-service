package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor.ThesisRequestToDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ThesisDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ThesisRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ThesisService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thesis")
public class ThesisController {

    @Autowired
    ThesisRequestToDto thesisRequestToDto;

    @Autowired
    ThesisService thesisService;

    //Get


    @GetMapping(path = "/{id}")
    public ThesisRest getThesis(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();

        ThesisDto thesisDto = thesisService.getThesisByThesisId(id);

        ThesisRest thesisRest = modelMapper.map(thesisDto, ThesisRest.class);

        return thesisRest;

    }

    @PostMapping(path = "/create/{id}")
    public ThesisRest createThesis(@PathVariable String id, @RequestBody ThesisDetailsRequestModel thesisDetails) {

        ModelMapper modelMapper = new ModelMapper();

        //ThesisDto thesisDto = thesisRequestToDto.convert(thesisDetails);
        ThesisDto thesisDto = modelMapper.map(thesisDetails, ThesisDto.class);

        ThesisDto createdThesis = thesisService.createThesis(id, thesisDto);

        ThesisRest thesisRest = modelMapper.map(createdThesis, ThesisRest.class);

        return thesisRest;


    }

    @PutMapping(path = "/update/{id}")
    public ThesisRest updateThesis(@PathVariable String id, @RequestBody ThesisDetailsRequestModel thesisDetails){

        ModelMapper modelMapper = new ModelMapper();

        ThesisDto thesisDto = modelMapper.map(thesisDetails, ThesisDto.class);

        ThesisDto updatedThesis = thesisService.updateThesis(id, thesisDto);

        ThesisRest returnValue = modelMapper.map(updatedThesis, ThesisRest.class);

        return returnValue;

    }


    @DeleteMapping(path = "/{id}")
    public String deleteThesis(@PathVariable String id){

        thesisService.deleteThesis(id);
        return "The thesis was deleted";
    }


    @PostMapping(path = "/apply")
    public String applyForThesis(){

        return "accept thesis was called";
    }




}
