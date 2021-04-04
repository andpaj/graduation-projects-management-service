package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamMemberRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.TeamRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.security.UserPrincipal;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.UserService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    TeamRepository teamRepository;

//    @Override
//    public UserDto getUser(String email) {
//        UserEntity userEntity = userRepository.findByEmail(email);
//
//        //if (userEntity == null) throw new UsernameNotFoundException(email);
//        ModelMapper modelMapper = new ModelMapper();
//
//        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
//
//
//        return returnValue;
//    }

    @Override
    public UserDto getUserByUserId(String userId)  {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        List<String> roles = new ArrayList<>();
        for (RoleEntity roleEntity: userEntity.getRoles()){
            roles.add(roleEntity.getName());
        }
        returnValue.setRoles(roles);

        return returnValue;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER_EMAIL.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public List<UserDto> getUsersWithProjectList() {

        ModelMapper modelMapper = new ModelMapper();

        List<UserDto> usersDto = new ArrayList<>();
        List<UserEntity> usersEntity = userRepository.findAll();

        for (UserEntity userEntity: usersEntity){

            UserDto userWithProjectsDto = modelMapper.map(userEntity, UserDto.class);
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
    public UserDto createUser(UserDto user,  List<String> groups) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        List<GroupEntity> groupEntityList = new ArrayList<>();


        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //change for loop

        for (String groupId : groups) {
            GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
            if (groupEntity == null) throw
                    new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());
            groupEntityList.add(groupEntity);
            groupEntity.getUsers().add(userEntity);

        }

        userEntity.setGroupEntities(groupEntityList);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamId(utils.generateTeamId(30));
        teamEntity.setTeamName(TeamEnum.STARTER_TEAM_NAME.getTeamEnum());

        TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
        teamMemberEntity.setTeamMemberId(utils.generateTeamMemberId(30));
        teamMemberEntity.setUser(userEntity);
        teamMemberEntity.setTeam(teamEntity);

        List<TeamMemberEntity> members = new ArrayList<>();
        members.add(teamMemberEntity);

        teamEntity.setTeamMembers(members);

        userEntity.setStarterTeam(teamEntity.getTeamId());

        UserEntity storedUserDetails = userRepository.save(userEntity);
        teamRepository.save(teamEntity);
        teamMemberRepository.save(teamMemberEntity);


        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

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
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        userRepository.delete(userEntity);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new UserPrincipal(userEntity);

//        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
//                true, true,
//                true, true,
//                new ArrayList<>());
    }
}
