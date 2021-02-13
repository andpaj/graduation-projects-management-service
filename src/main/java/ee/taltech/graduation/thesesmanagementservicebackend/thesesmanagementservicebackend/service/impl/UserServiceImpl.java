package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.DepartmentRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.UserService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserWithProjectsDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        //if (userEntity == null) throw new UsernameNotFoundException(email);
        ModelMapper modelMapper = new ModelMapper();

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId)  {

        UserEntity userEntity = userRepository.findByUserId(userId);
       // if (userEntity == null) throw new UsernameNotFoundException(email);
        ModelMapper modelMapper = new ModelMapper();
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public List<UserWithProjectsDto> getUsersWithProjectList() {

        ModelMapper modelMapper = new ModelMapper();

        List<UserWithProjectsDto> usersDto = new ArrayList<>();
        List<UserEntity> usersEntity = userRepository.findAll();

        for (UserEntity userEntity: usersEntity){

            UserWithProjectsDto userWithProjectsDto = modelMapper.map(userEntity, UserWithProjectsDto.class);
            usersDto.add(userWithProjectsDto);
        }

        return usersDto;

    }

    @Override
    public List<UserDto> getUsers() {

        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> usersTest = new ArrayList<>();
        List<UserEntity> usersEntity = userRepository.findAll();

        for (UserEntity userEntity: usersEntity){

            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            usersTest.add(userDto);
        }

        return usersTest;


    }

    @Override
    public UserDto createUser(UserDto user, String department) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new ServiceException("Record already exists");

        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentName(department);
        if (departmentEntity == null) throw new ServiceException("Department with that name does not exist");

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword("encryptedPassword");
        userEntity.setDepartment(departmentEntity);
        departmentEntity.getUsers().add(userEntity);

        UserEntity storedUserDetails = userRepository.save(userEntity);
        departmentRepository.save(departmentEntity);

        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedUserDetails = userRepository.save(userEntity);
        ModelMapper modelMapper = new ModelMapper();
        UserDto returnValue = modelMapper.map(updatedUserDetails, UserDto.class);
        return returnValue;


    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userRepository.delete(userEntity);

    }
}
