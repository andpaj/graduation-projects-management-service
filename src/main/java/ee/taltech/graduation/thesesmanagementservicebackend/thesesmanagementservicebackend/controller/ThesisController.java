package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.convertor.ThesisRequestToDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.ThesisDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ThesisRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.ThesisService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.ThesisDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/create")
    public ThesisRest createThesis(@RequestBody ThesisDetailsRequestModel thesisDetails) {

        ModelMapper modelMapper = new ModelMapper();

        ThesisDto thesisDto = thesisRequestToDto.convert(thesisDetails);

        ThesisDto createdThesis = thesisService.createThesis(thesisDto);

        ThesisRest thesisRest = modelMapper.map(createdThesis, ThesisRest.class);

        return thesisRest;


    }

    @PutMapping()
    public String updateThesis(){
        return "update Thesis was called";
    }

    @DeleteMapping()
    public String deleteThesis(){

        return "delete Thesis was called";
    }


    @PostMapping(path = "/apply")
    public String applyForThesis(){

        return "accept thesis was called";
    }




}
