package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.DepartmentRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.DepartmentService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
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
    public DepartmentDto getDepartmentByDepartmentId(String departmentId) {
        ModelMapper modelMapper = new ModelMapper();

        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentId(departmentId);
        //check null

        DepartmentDto departmentDto = modelMapper.map(departmentEntity, DepartmentDto.class);

        return departmentDto;


    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        ModelMapper modelMapper = new ModelMapper();
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);

        departmentEntity.setDepartmentId(utils.generateDepartmentId(30));

        DepartmentEntity createdDepartment = departmentRepository.save(departmentEntity);

        DepartmentDto returnValue = modelMapper.map(createdDepartment, DepartmentDto.class);

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
    public void deleteDepartment(String departmentId) {
        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentId(departmentId);
        if (departmentEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        departmentRepository.delete(departmentEntity);

    }
}
