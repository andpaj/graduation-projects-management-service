package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.DepartmentDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.DepartmentRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.DepartmentService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    @GetMapping(path = "/{id}")
    public DepartmentRest getDepartment(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();

        DepartmentDto departmentDto = departmentService.getDepartmentByDepartmentId(id);

        DepartmentRest departmentRest = modelMapper.map(departmentDto, DepartmentRest.class);

        return departmentRest;



    }

    @PostMapping(path = "/create")
    public DepartmentRest createDepartment(@RequestBody DepartmentDetailsRequestModel departmentDetails) {

        ModelMapper modelMapper = new ModelMapper();
        DepartmentDto departmentDto = modelMapper.map(departmentDetails, DepartmentDto.class);

        DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
        DepartmentRest returnValue = modelMapper.map(createdDepartment, DepartmentRest.class);

        return returnValue;


    }

    @PutMapping()
    public String updateDepartment(){
        return "update Department was called";
    }

    @DeleteMapping()
    public String deleteDepartment(){

        return "delete Department was called";
    }




}