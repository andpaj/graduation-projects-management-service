package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.DepartmentRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.DepartmentService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentWithUsersDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    Utils utils;

    @Override
    public DepartmentWithUsersDto getDepartmentByDepartmentId(String departmentId) {
        ModelMapper modelMapper = new ModelMapper();

        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentId(departmentId);
        //check null

        DepartmentWithUsersDto departmentWithUsersDto = modelMapper.map(departmentEntity, DepartmentWithUsersDto.class);

        return departmentWithUsersDto;


    }

    @Override
    public DepartmentWithUsersDto createDepartment(DepartmentWithUsersDto departmentWithUsersDto) {

        ModelMapper modelMapper = new ModelMapper();
        DepartmentEntity departmentEntity = modelMapper.map(departmentWithUsersDto, DepartmentEntity.class);

        departmentEntity.setDepartmentId(utils.generateDepartmentId(30));

        DepartmentEntity createdDepartment = departmentRepository.save(departmentEntity);

        DepartmentWithUsersDto returnValue = modelMapper.map(createdDepartment, DepartmentWithUsersDto.class);

        return returnValue;

    }

    @Override
    public List<DepartmentDto> getDepartments() {

        ModelMapper modelMapper = new ModelMapper();

        List<DepartmentDto> departmentsDto = new ArrayList<>();

        List<DepartmentEntity> departmentsEntity = departmentRepository.findAll();

        for (DepartmentEntity departmentEntity: departmentsEntity){

            DepartmentDto departmentDto = modelMapper.map(departmentEntity, DepartmentDto.class);
            departmentsDto.add(departmentDto);
        }

        return departmentsDto;


    }

    @Override
    public List<DepartmentWithUsersDto> getDepartmentsWithUsers() {

        ModelMapper modelMapper = new ModelMapper();

        List<DepartmentWithUsersDto> departmentsDto = new ArrayList<>();

        List<DepartmentEntity> departmentsEntity = departmentRepository.findAll();

        for (DepartmentEntity departmentEntity: departmentsEntity){

            DepartmentWithUsersDto departmentWithUsersDto = modelMapper.map(departmentEntity, DepartmentWithUsersDto.class);
            departmentsDto.add(departmentWithUsersDto);
        }

        return departmentsDto;
    }

    @Override
    public void deleteDepartment(String departmentId) {
        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentId(departmentId);
        if (departmentEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        departmentRepository.delete(departmentEntity);

    }
}
