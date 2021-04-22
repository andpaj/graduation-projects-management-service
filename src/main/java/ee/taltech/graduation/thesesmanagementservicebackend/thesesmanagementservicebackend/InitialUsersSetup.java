package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.*;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.ProjectEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.Roles;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.TeamEnum;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.enums.UserEnum;
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
    UserGroupRoleRepository userGroupRoleRepository;

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
        studentUser.setGraduationLevel(UserEnum.GRADUATION_LEVEL_BACHELOR.getUserEnum());
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
        userEntity.setGraduationLevel(UserEnum.GRADUATION_LEVEL_MASTER.getUserEnum());
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
            studentUser.setGroupEntities(groups);
            userEntity1.setGroupEntities(groups);
            userEntity2.setGroupEntities(groups);



            UserGroupRoleEntity userGroupRoleEntity = new UserGroupRoleEntity();
            userGroupRoleEntity.setUser(studentUser);
            userGroupRoleEntity.setGroupEntity(userGroup);
            userGroupRoleEntity.setRole(roleStudent);
            userGroupRoleRepository.save(userGroupRoleEntity);

        UserGroupRoleEntity userGroupRoleEntity1 = new UserGroupRoleEntity();
        userGroupRoleEntity1.setUser(teacherUser);
        userGroupRoleEntity1.setGroupEntity(userGroup);
        userGroupRoleEntity1.setRole(roleTeacher);
        userGroupRoleRepository.save(userGroupRoleEntity1);


        //Real teachers from document
        UserEntity einar = new UserEntity();
        einar.setFirstName("Einar");
        einar.setLastName("Meister");
        einar.setEmail("einar@test.com");
        einar.setGroupEntities(groups);
        einar.setUserId("einarTeacher");
        einar.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        einar.setRoles(List.of(roleTeacher));
        userRepository.save(einar);

        UserEntity alex = new UserEntity();
        alex.setFirstName("Alex");
        alex.setLastName("Norta");
        alex.setEmail("alex@test.com");
        alex.setGroupEntities(groups);
        alex.setUserId("alexTeacher");
        alex.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        alex.setRoles(List.of(roleTeacher));
        userRepository.save(alex);

        UserEntity hellis = new UserEntity();
        hellis.setFirstName("Hellis");
        hellis.setLastName("Tamm");
        hellis.setGroupEntities(groups);
        hellis.setEmail("hellis@test.com");
        hellis.setUserId("hellisTeacher");
        hellis.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        hellis.setRoles(List.of(roleTeacher));
        userRepository.save(hellis);


        UserEntity silvio = new UserEntity();
        silvio.setFirstName("Silvio");
        silvio.setLastName("Capobianco");
        silvio.setEmail("silvio@test.com");
        silvio.setGroupEntities(groups);
        silvio.setUserId("silvioTeacher");
        silvio.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        silvio.setRoles(List.of(roleTeacher));
        userRepository.save(silvio);

        UserEntity niccolo = new UserEntity();
        niccolo.setFirstName("Niccolò");
        niccolo.setLastName("Veltri");
        niccolo.setGroupEntities(groups);
        niccolo.setEmail("niccolo@test.com");
        niccolo.setUserId("niccoloTeacher");
        niccolo.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        niccolo.setRoles(List.of(roleTeacher));
        userRepository.save(niccolo);

        UserEntity sadok = new UserEntity();
        sadok.setFirstName("Sadok");
        sadok.setLastName("BEN YAHIA");
        sadok.setGroupEntities(groups);
        sadok.setEmail("sadok@test.com");
        sadok.setUserId("sadokTeacher");
        sadok.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        sadok.setRoles(List.of(roleTeacher));
        userRepository.save(sadok);

        UserEntity juri = new UserEntity();
        juri.setFirstName("Jüri");
        juri.setLastName("Vain");
        juri.setEmail("juri@test.com");
        juri.setGroupEntities(groups);
        juri.setUserId("juriTeacher");
        juri.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        juri.setRoles(List.of(roleTeacher));
        userRepository.save(juri);

        UserEntity sven = new UserEntity();
        sven.setFirstName("Sven");
        sven.setLastName("Nõmm");
        sven.setEmail("sven@test.com");
        sven.setGroupEntities(groups);
        sven.setUserId("svenTeacher");
        sven.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        sven.setRoles(List.of(roleTeacher));
        userRepository.save(sven);

        UserEntity erki = new UserEntity();
        erki.setFirstName("Erki");
        erki.setLastName("Eessaar");
        erki.setGroupEntities(groups);
        erki.setEmail("erki@test.com");
        erki.setUserId("erkiTeacher");
        erki.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        erki.setRoles(List.of(roleTeacher));
        userRepository.save(erki);

        UserEntity arvo = new UserEntity();
        arvo.setFirstName("Arvo");
        arvo.setLastName("Kaldmäe");
        arvo.setGroupEntities(groups);
        arvo.setEmail("arvo@test.com");
        arvo.setUserId("arvoTeacher");
        arvo.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        arvo.setRoles(List.of(roleTeacher));
        userRepository.save(arvo);

        UserEntity pawel = new UserEntity();
        pawel.setFirstName("Pawel");
        pawel.setLastName("Sobocinski");
        pawel.setEmail("pawel@test.com");
        pawel.setGroupEntities(groups);
        pawel.setUserId("pawelTeacher");
        pawel.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        pawel.setRoles(List.of(roleTeacher));
        userRepository.save(pawel);

        UserEntity maksym = new UserEntity();
        maksym.setFirstName("Maksym");
        maksym.setLastName("Bortin");
        maksym.setEmail("maksym@test.com");
        maksym.setGroupEntities(groups);
        maksym.setUserId("maksymTeacher");
        maksym.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        maksym.setRoles(List.of(roleTeacher));
        userRepository.save(maksym);

        UserEntity martin = new UserEntity();
        martin.setFirstName("Martin");
        martin.setLastName("Rebane");
        martin.setEmail("martin@test.com");
        martin.setGroupEntities(groups);
        martin.setUserId("martinTeacher");
        martin.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        martin.setRoles(List.of(roleTeacher));
        userRepository.save(martin);

        UserEntity belikov = new UserEntity();
        belikov.setFirstName("Juri");
        belikov.setLastName("Belikov");
        belikov.setEmail("belikov@test.com");
        belikov.setGroupEntities(groups);
        belikov.setUserId("belikovTeacher");
        belikov.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        belikov.setRoles(List.of(roleTeacher));
        userRepository.save(belikov);

        UserEntity niels = new UserEntity();
        niels.setFirstName("Niels");
        niels.setLastName("Voorneveld");
        niels.setEmail("niels@test.com");
        niels.setUserId("nielsTeacher");
        niels.setGroupEntities(groups);
        niels.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        niels.setRoles(List.of(roleTeacher));
        userRepository.save(niels);

        UserEntity edward = new UserEntity();
        edward.setFirstName("Edward");
        edward.setLastName("Morehouse");
        edward.setEmail("edward@test.com");
        edward.setGroupEntities(groups);
        edward.setUserId("edwardTeacher");
        edward.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        edward.setRoles(List.of(roleTeacher));
        userRepository.save(edward);


        ///////////////////////////////////////////







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


        TeamEntity testTeam3 = new TeamEntity();
        testTeam3.setTeamName("TestTeam3");
        testTeam3.setTeamId("testTeam3");
        testTeam3.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        testTeam3.setAuthorId(teacherUser.getUserId());

        TeamMemberEntity testTeamMember3 = new TeamMemberEntity();
        testTeamMember3.setTeamMemberId("testTeamMember3");
        testTeamMember3.setUser(teacherUser);
        testTeamMember3.setTeam(testTeam3);

        teacherUser.setStarterTeam(testTeam3.getTeamId());

        teamRepository.save(testTeam3);
        teamMemberRepository.save(testTeamMember3);


        TeamEntity testTeam4 = new TeamEntity();
        testTeam4.setTeamName("TestTeam4");
        testTeam4.setTeamId("testTeam4");
        testTeam4.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        testTeam4.setAuthorId(userEntity2.getUserId());

        TeamMemberEntity testTeamMember4 = new TeamMemberEntity();
        testTeamMember4.setTeamMemberId("testTeamMember3");
        testTeamMember4.setUser(userEntity2);
        testTeamMember4.setTeam(testTeam4);

        userEntity2.setStarterTeam(testTeam4.getTeamId());

        teamRepository.save(testTeam4);
        teamMemberRepository.save(testTeamMember4);


        TeamEntity testTeam5 = new TeamEntity();
        testTeam5.setTeamName("TestTeam5");
        testTeam5.setTeamId("testTeam5");
        testTeam5.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
        testTeam5.setAuthorId(userEntity1.getUserId());

        TeamMemberEntity testTeamMember5 = new TeamMemberEntity();
        testTeamMember5.setTeamMemberId("testTeamMember3");
        testTeamMember5.setUser(userEntity1);
        testTeamMember5.setTeam(testTeam5);

        userEntity1.setStarterTeam(testTeam5.getTeamId());

        teamRepository.save(testTeam5);
        teamMemberRepository.save(testTeamMember5);



        userRepository.save(teacherUser);
        userRepository.save(userEntity);
        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        ProjectEntity project = new ProjectEntity();
        project.setAcceptingTime(new Date());
        project.setCreatingTime(new Date());
        project.setDegree(ProjectEnum.DEGREE_BACHELOR.getProjectEnum());
        project.setDescription("some description to describe current project");
        project.setLanguage("Estonian");
        project.setProjectId("project1");
        project.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
        project.setStudentAmount(2);
        project.setTitle("Graduation projects management service");
        project.setUser(userRepository.findByUserId("testTeacher"));
        projectRepository.save(project);

        ProjectEntity project2 = new ProjectEntity();
        project2.setAcceptingTime(new Date());
        project2.setCreatingTime(new Date());
        project2.setDegree(ProjectEnum.DEGREE_MASTER.getProjectEnum());
        project2.setDescription("some description to describe current project");
        project2.setLanguage("English");
        project2.setProjectId("project2");
        project2.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
        project2.setStudentAmount(2);
        project2.setTitle("ToDo list on React");
        project2.setUser(userRepository.findByUserId("testTeacher"));
        projectRepository.save(project2);

        ProjectEntity project3 = new ProjectEntity();
        project3.setAcceptingTime(new Date());
        project3.setCreatingTime(new Date());
        project3.setDegree(ProjectEnum.DEGREE_BACHELOR.getProjectEnum());
        project3.setDescription("some description to describe current project");
        project3.setLanguage("English");
        project3.setProjectId("project3");
        project3.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
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
