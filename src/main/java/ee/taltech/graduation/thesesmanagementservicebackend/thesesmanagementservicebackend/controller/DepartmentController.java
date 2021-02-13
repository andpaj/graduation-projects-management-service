package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.request.DepartmentDetailsRequestModel;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.DepartmentRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.DepartmentWithUsersRest;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.DepartmentService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentWithUsersDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    @GetMapping(path = "/{id}")
    public DepartmentRest getDepartment(@PathVariable String id){

        ModelMapper modelMapper = new ModelMapper();

        DepartmentWithUsersDto departmentWithUsersDto = departmentService.getDepartmentByDepartmentId(id);

        DepartmentRest departmentRest = modelMapper.map(departmentWithUsersDto, DepartmentRest.class);

        return departmentRest;



    }

    @GetMapping()
    public List<DepartmentRest> getDepartments(){
        ModelMapper modelMapper = new ModelMapper();
        List<DepartmentRest> departmentRestList = new ArrayList<>();
        List<DepartmentDto> listOfDepartments = departmentService.getDepartments();


        for (DepartmentDto departmentDto: listOfDepartments) {

            DepartmentRest departmentRest = modelMapper.map(departmentDto, DepartmentRest.class);
            departmentRestList.add(departmentRest);
        }

        return departmentRestList;
    }

    @GetMapping(path = "/withUsers")
    public List<DepartmentWithUsersRest> getDepartmentsWithUsers(){
        ModelMapper modelMapper = new ModelMapper();
        List<DepartmentWithUsersRest> departmentWithUsersRestList = new ArrayList<>();

        List<DepartmentWithUsersDto> departmentWithUsersDto = departmentService.getDepartmentsWithUsers();

        for (DepartmentWithUsersDto departmentWithUsersDtoIter: departmentWithUsersDto){

            DepartmentWithUsersRest departmentWithUsersRest  = modelMapper.map(departmentWithUsersDtoIter, DepartmentWithUsersRest.class);
            departmentWithUsersRestList.add(departmentWithUsersRest);

        }

        return departmentWithUsersRestList;

    }

    @PostMapping(path = "/create")
    public DepartmentRest createDepartment(@RequestBody DepartmentDetailsRequestModel departmentDetails) {

        ModelMapper modelMapper = new ModelMapper();
        DepartmentWithUsersDto departmentWithUsersDto = modelMapper.map(departmentDetails, DepartmentWithUsersDto.class);

        DepartmentWithUsersDto createdDepartment = departmentService.createDepartment(departmentWithUsersDto);
        DepartmentRest returnValue = modelMapper.map(createdDepartment, DepartmentRest.class);

        return returnValue;


    }

    @PutMapping()
    public String updateDepartment(){
        return "update Department was called";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteDepartment(@PathVariable String id){

        departmentService.deleteDepartment(id);

        return id;
    }




}
