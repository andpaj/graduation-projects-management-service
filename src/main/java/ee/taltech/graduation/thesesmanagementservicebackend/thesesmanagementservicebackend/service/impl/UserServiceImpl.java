package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.impl;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.controller.UserController;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.exception.ServiceException;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.model.response.ErrorMessages;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.security.UserPrincipal;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.service.UserService;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.UserDto;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.Roles;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.UserEnum;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    TeamRepository teamRepository;


    @Override
    public UserDto getUserByUserId(String userId)  {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());
        }
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
    public List<UserDto> getSupervisors() {
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> supervisorsDto = new ArrayList<>();
        List<UserEntity> allUsers = userRepository.findAll();

        List<UserEntity> supervisorsEntity = new ArrayList<>();

        for (UserEntity userEntity: allUsers){
            for (RoleEntity roleEntity: userEntity.getRoles()){
                if (roleEntity.getName().equals(Roles.ROLE_TEACHER.name())){
                    supervisorsEntity.add(userEntity);
                }
            }
        }

        for (UserEntity userEntity: supervisorsEntity){

            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            supervisorsDto.add(userDto);
        }

        return supervisorsDto;
    }

    @Override
    public List<UserDto> getStudents() {
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> studentsDto = new ArrayList<>();
        List<UserEntity> allUsers = userRepository.findAll();

        List<UserEntity> studentsEntity = new ArrayList<>();

        for (UserEntity userEntity: allUsers){
            for (RoleEntity roleEntity: userEntity.getRoles()){
                if (roleEntity.getName().equals(Roles.ROLE_STUDENT.name())){
                    studentsEntity.add(userEntity);
                }
            }
        }

        for (UserEntity userEntity: studentsEntity){

            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            studentsDto.add(userDto);
        }

        return studentsDto;
    }

//    @Override
//    public UserDto createUserMethodForInitUsers(UserDto userDto, List<String> groups, List<String> roles) {
//        if (userRepository.findByEmail(userDto.getEmail()) != null)
//            throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//
//        List<GroupEntity> groupEntityList = new ArrayList<>();
//        List<RoleEntity> roleEntityList = new ArrayList<>();
//
//
//        ModelMapper modelMapper = new ModelMapper();
//        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
//
//        userEntity.setUserId(utils.generateUserId(30));
//        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//
//
//        //adding groups to user
//        for (String groupId : groups) {
//            GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
//            if (groupEntity == null) throw
//                    new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());
//            groupEntityList.add(groupEntity);
//            groupEntity.getUsers().add(userEntity);
//
//        }
//
//
//        for (String role: roles){
//            RoleEntity roleEntity = roleRepository.findByName(role);
//            if (roleEntity == null)  throw
//                    new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());
//            roleEntityList.add(roleEntity);
//            roleEntity.getUsers().add(userEntity);
//
//        }
//
//        userEntity.setGroupEntities(groupEntityList);
//        userEntity.setRoles(roleEntityList);
//
//        //create team for solo applications
//        TeamEntity teamEntity = new TeamEntity();
//        teamEntity.setTeamId(utils.generateTeamId(30));
//        teamEntity.setTeamName(TeamEnum.STARTER_TEAM_NAME.getTeamEnum());
//        teamEntity.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//
//        TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
//        teamMemberEntity.setTeamMemberId(utils.generateTeamMemberId(30));
//        teamMemberEntity.setUser(userEntity);
//        teamMemberEntity.setTeam(teamEntity);
//
//        List<TeamMemberEntity> members = new ArrayList<>();
//        members.add(teamMemberEntity);
//
//        teamEntity.setTeamMembers(members);
//
//        userEntity.setStarterTeam(teamEntity.getTeamId());
//
//        UserEntity storedUserDetails = userRepository.save(userEntity);
//        teamRepository.save(teamEntity);
//        teamMemberRepository.save(teamMemberEntity);
//
//
//        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
//
//        return returnValue;
//    }

    @Override
    public UserDto createUser(UserDto userDto, List<String> groups, List<String> roles) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        List<GroupEntity> groupEntityList = new ArrayList<>();
        List<RoleEntity> roleEntityList = new ArrayList<>();


        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));


        //adding groups to user
        for (String groupId : groups) {
            GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
            if (groupEntity == null) throw
                    new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());
            groupEntityList.add(groupEntity);
            groupEntity.getUsers().add(userEntity);

        }


        for (String role: roles){
            RoleEntity roleEntity = roleRepository.findByName(role);
            if (roleEntity == null)  throw
                    new ServiceException(ErrorMessages.NO_RECORD_FOUND_GROUP.getErrorMessage());
            roleEntityList.add(roleEntity);
            roleEntity.getUsers().add(userEntity);

        }

        userEntity.setGroupEntities(groupEntityList);
        userEntity.setRoles(roleEntityList);
        //create team for solo applications
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamId(utils.generateTeamId(30));
        teamEntity.setTeamName(TeamEnum.STARTER_TEAM_NAME.getTeamEnum());
        teamEntity.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());

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
    public UserDto updateUser(String userId, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) throw
                new ServiceException(ErrorMessages.NO_RECORD_FOUND_USER.getErrorMessage());

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

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
