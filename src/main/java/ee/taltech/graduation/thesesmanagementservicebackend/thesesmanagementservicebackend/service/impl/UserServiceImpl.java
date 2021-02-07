package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.UserService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserWithThesesDto;
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
    Utils utils;

    @Override
    public UserDto getUserByUserId(String userId)  {

        UserEntity userEntity = userRepository.findByUserId(userId);

       // if (userEntity == null) throw new Exception("User name not found");
        ModelMapper modelMapper = new ModelMapper();
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public List<UserWithThesesDto> getUsersWithThesesList() {

        ModelMapper modelMapper = new ModelMapper();


        List<UserWithThesesDto> usersDto = new ArrayList<>();

        List<UserEntity> usersEntity = userRepository.findAll();

        for (UserEntity userEntity: usersEntity){

            UserWithThesesDto userWithThesesDto = modelMapper.map(userEntity, UserWithThesesDto.class);
            usersDto.add(userWithThesesDto);
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
    public UserDto createUser(UserDto user) {
//        if (userRepository.findByEmail(user.getEmail()) != null)
//            throw new UserServiceException("Record already exists");

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword("encryptedPassword");

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserEntity userEntity = userRepository.findByUserId(userId);

//        if (userEntity == null) throw
//                new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

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
//        if (userEntity == null) throw
//                new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userRepository.delete(userEntity);

    }
}
