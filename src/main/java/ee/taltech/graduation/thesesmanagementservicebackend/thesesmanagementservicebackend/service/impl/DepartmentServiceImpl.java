package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.DepartmentRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.DepartmentService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
