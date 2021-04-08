package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.Roles;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//Class which called after application started
@Component
public class InitialUsersSetup {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event){

        AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
        AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
        AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

        RoleEntity roleStudent = createRole(Roles.ROLE_STUDENT.name(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleTeacher = createRole(Roles.ROLE_TEACHER.name(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        if (roleAdmin == null) return;


        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("Andres");
        adminUser.setLastName("Pajuste");
        adminUser.setEmail("admin@test.com");
        adminUser.setUserId("testAdmin");
        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        adminUser.setRoles(List.of(roleAdmin));

        userRepository.save(adminUser);

        UserEntity studentUser = new UserEntity();
        studentUser.setFirstName("Oleg");
        studentUser.setLastName("Kartašov");
        studentUser.setEmail("student@test.com");
        studentUser.setUserId("testStudent");
        studentUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        studentUser.setRoles(List.of(roleStudent));

        userRepository.save(studentUser);

        UserEntity teacherUser = new UserEntity();
        teacherUser.setFirstName("Teacher");
        teacherUser.setLastName("Test");
        teacherUser.setEmail("teacher@test.com");
        teacherUser.setUserId("testTeacher");
        teacherUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        teacherUser.setRoles(List.of(roleTeacher));

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("testAgo");
        userEntity.setFirstName("Ago");
        userEntity.setLastName("Luberg");
        userEntity.setEmail("testAgo@test.com");
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        userEntity.setRoles(List.of(roleTeacher, roleStudent));

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserId("testMarko");
        userEntity1.setFirstName("Marko");
        userEntity1.setLastName("Kääramees");
        userEntity1.setEmail("testMarko@test.com");
        userEntity1.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        userEntity1.setRoles(List.of(roleTeacher));

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId("testTanel");
        userEntity2.setFirstName("Tanel");
        userEntity2.setLastName("Tammet");
        userEntity2.setEmail("testTanel@test.com");
        userEntity2.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        userEntity2.setRoles(List.of(roleTeacher));

            GroupEntity userGroup = groupRepository.findByGroupId("IAIB");
            GroupEntity userGroup2 = groupRepository.findByGroupId("school_information_technologies");
            List<GroupEntity> groups = new ArrayList<>(List.of(userGroup, userGroup2));
            teacherUser.setGroupEntities(groups);
            userEntity.setGroupEntities(groups);
//        userEntity1.setGroupEntity(userGroup);
//        userEntity2.setGroupEntity(userGroup);
//        userGroup.getUsers().add(teacherUser);
//        userGroup.getUsers().add(userEntity);
//        userGroup.getUsers().add(userEntity1);
//        userGroup.getUsers().add(userEntity2);


        TeamEntity testTeam1 = new TeamEntity();
        testTeam1.setTeamName("TestTeam1");
        testTeam1.setTeamId("testTeam1");
        testTeam1.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        testTeam1.setAuthorId(studentUser.getUserId());

        TeamMemberEntity testTeamMember1 = new TeamMemberEntity();
        testTeamMember1.setTeamMemberId("testTeamMember1");
        testTeamMember1.setUser(studentUser);
        testTeamMember1.setTeam(testTeam1);

        studentUser.setStarterTeam(testTeam1.getTeamId());

        teamRepository.save(testTeam1);
        teamMemberRepository.save(testTeamMember1);



        TeamEntity testTeam2 = new TeamEntity();
        testTeam2.setTeamName("TestTeam2");
        testTeam2.setTeamId("testTeam2");
        testTeam2.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        testTeam2.setAuthorId(userEntity.getUserId());

        TeamMemberEntity testTeamMember2 = new TeamMemberEntity();
        testTeamMember2.setTeamMemberId("testTeamMember2");
        testTeamMember2.setUser(userEntity);
        testTeamMember2.setTeam(testTeam2);

        userEntity.setStarterTeam(testTeam2.getTeamId());

        teamRepository.save(testTeam2);
        teamMemberRepository.save(testTeamMember2);



        userRepository.save(teacherUser);
        userRepository.save(userEntity);
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        ProjectEntity project = new ProjectEntity();
        project.setAcceptingTime(new Date());
        project.setCreatingTime(new Date());
        project.setDegree("Bachelor");
        project.setDescription("some description to describe current project");
        project.setDifficultyRating(2);
        project.setLanguage("Estonian");
        project.setProjectId("project1");
        project.setStatus("Available");
        project.setStudentAmount(2);
        project.setTitle("Graduation projects management service");
        project.setUser(userRepository.findByUserId("testTeacher"));
        projectRepository.save(project);

        ProjectEntity project2 = new ProjectEntity();
        project2.setAcceptingTime(new Date());
        project2.setCreatingTime(new Date());
        project2.setDegree("Master");
        project2.setDescription("some description to describe current project");
        project2.setDifficultyRating(2);
        project2.setLanguage("English");
        project2.setProjectId("project2");
        project2.setStatus("Available");
        project2.setStudentAmount(2);
        project2.setTitle("ToDo list on React");
        project2.setUser(userRepository.findByUserId("testTeacher"));
        projectRepository.save(project2);

        ProjectEntity project3 = new ProjectEntity();
        project3.setAcceptingTime(new Date());
        project3.setCreatingTime(new Date());
        project3.setDegree("Bachelor");
        project3.setDescription("some description to describe current project");
        project3.setDifficultyRating(2);
        project3.setLanguage("English");
        project3.setProjectId("project3");
        project3.setStatus("Available");
        project3.setStudentAmount(2);
        project3.setTitle("TalTech Bot for Students");
        project3.setUser(userRepository.findByUserId("testTeacher"));
        projectRepository.save(project3);

    }

    @Transactional
    private AuthorityEntity createAuthority(String name){

        AuthorityEntity authority = authorityRepository.findByName(name);
        if (authority == null){
            authority = new AuthorityEntity(name);
            authorityRepository.save(authority);
        }

        return authority;

    }

    @Transactional
    private RoleEntity createRole(String name, List<AuthorityEntity> authorities){

        RoleEntity role = roleRepository.findByName(name);
        if (role == null){
            role = new RoleEntity(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }

        return role;



    }


}
