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
    public void onApplicationEvent(ApplicationReadyEvent event) {

//        AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
//        AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
//        AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
//
//        RoleEntity roleStudent = createRole(Roles.ROLE_STUDENT.name(), Arrays.asList(readAuthority, writeAuthority));
//        RoleEntity roleTeacher = createRole(Roles.ROLE_TEACHER.name(), Arrays.asList(readAuthority, writeAuthority));
//        RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
//
//        if (roleAdmin == null) return;
//
//
//        UserEntity adminUser = new UserEntity();
//        adminUser.setFirstName("Andres");
//        adminUser.setLastName("Pajuste");
//        adminUser.setEmail("admin@test.com");
//        adminUser.setUserId("testAdmin");
//        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        adminUser.setRoles(List.of(roleAdmin));
//
//        userRepository.save(adminUser);
//
//        UserEntity studentUser = new UserEntity();
//        studentUser.setFirstName("Oleg");
//        studentUser.setLastName("Kartašov");
//        studentUser.setEmail("student@test.com");
//        studentUser.setUserId("testStudent");
//        studentUser.setGraduationLevel(UserEnum.GRADUATION_LEVEL_BACHELOR.getUserEnum());
//        studentUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        studentUser.setRoles(List.of(roleStudent));
//
//        userRepository.save(studentUser);
//
//        UserEntity teacherUser = new UserEntity();
//        teacherUser.setFirstName("Teacher");
//        teacherUser.setLastName("Test");
//        teacherUser.setEmail("teacher@test.com");
//        teacherUser.setUserId("testTeacher");
//        teacherUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        teacherUser.setRoles(List.of(roleTeacher));
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId("testAgo");
//        userEntity.setFirstName("Ago");
//        userEntity.setLastName("Luberg");
//        userEntity.setEmail("testAgo@test.com");
//        userEntity.setGraduationLevel(UserEnum.GRADUATION_LEVEL_MASTER.getUserEnum());
//        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        userEntity.setRoles(List.of(roleTeacher));
//
//        UserEntity userEntity1 = new UserEntity();
//        userEntity1.setUserId("testMarko");
//        userEntity1.setFirstName("Marko");
//        userEntity1.setLastName("Kääramees");
//        userEntity1.setEmail("testMarko@test.com");
//        userEntity1.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        userEntity1.setRoles(List.of(roleTeacher));
//
//        UserEntity userEntity2 = new UserEntity();
//        userEntity2.setUserId("testTanel");
//        userEntity2.setFirstName("Tanel");
//        userEntity2.setLastName("Tammet");
//        userEntity2.setEmail("testTanel@test.com");
//        userEntity2.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        userEntity2.setRoles(List.of(roleTeacher));
//
//            GroupEntity userGroup = groupRepository.findByGroupId("IAIB");
//            GroupEntity userGroup1 = groupRepository.findByGroupId("IVSM");
//            GroupEntity userGroup2 = groupRepository.findByGroupId("YAFM");
//            GroupEntity userGroup3 = groupRepository.findByGroupId("school_information_technologies");
//            List<GroupEntity> groups = new ArrayList<>(List.of(userGroup, userGroup2, userGroup1, userGroup3));
//            teacherUser.setGroupEntities(groups);
//            userEntity.setGroupEntities(groups);
//            studentUser.setGroupEntities(groups);
//            userEntity1.setGroupEntities(groups);
//            userEntity2.setGroupEntities(groups);
//
//
//
//            UserGroupRoleEntity userGroupRoleEntity = new UserGroupRoleEntity();
//            userGroupRoleEntity.setUser(studentUser);
//            userGroupRoleEntity.setGroupEntity(userGroup);
//            userGroupRoleEntity.setRole(roleStudent);
//            userGroupRoleRepository.save(userGroupRoleEntity);
//
//        UserGroupRoleEntity userGroupRoleEntity1 = new UserGroupRoleEntity();
//        userGroupRoleEntity1.setUser(teacherUser);
//        userGroupRoleEntity1.setGroupEntity(userGroup);
//        userGroupRoleEntity1.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity1);
//
//
//        //Real teachers from document
//        UserEntity einar = new UserEntity();
//        einar.setFirstName("Einar");
//        einar.setLastName("Meister");
//        einar.setEmail("einar@test.com");
//        einar.setGroupEntities(groups);
//        einar.setUserId("einarTeacher");
//        einar.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        einar.setRoles(List.of(roleTeacher));
//        userRepository.save(einar);
//
//        UserGroupRoleEntity userGroupRoleEntity3 = new UserGroupRoleEntity();
//        userGroupRoleEntity3.setUser(einar);
//        userGroupRoleEntity3.setGroupEntity(userGroup);
//        userGroupRoleEntity3.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity3);
//
//        UserEntity alex = new UserEntity();
//        alex.setFirstName("Alex");
//        alex.setLastName("Norta");
//        alex.setEmail("alex@test.com");
//        alex.setGroupEntities(groups);
//        alex.setUserId("alexTeacher");
//        alex.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        alex.setRoles(List.of(roleTeacher));
//        userRepository.save(alex);
//
//        UserGroupRoleEntity userGroupRoleEntity4 = new UserGroupRoleEntity();
//        userGroupRoleEntity4.setUser(alex);
//        userGroupRoleEntity4.setGroupEntity(userGroup);
//        userGroupRoleEntity4.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity4);
//
//        UserEntity hellis = new UserEntity();
//        hellis.setFirstName("Hellis");
//        hellis.setLastName("Tamm");
//        hellis.setGroupEntities(groups);
//        hellis.setEmail("hellis@test.com");
//        hellis.setUserId("hellisTeacher");
//        hellis.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        hellis.setRoles(List.of(roleTeacher));
//        userRepository.save(hellis);
//
//        UserGroupRoleEntity userGroupRoleEntity5 = new UserGroupRoleEntity();
//        userGroupRoleEntity5.setUser(hellis);
//        userGroupRoleEntity5.setGroupEntity(userGroup);
//        userGroupRoleEntity5.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity5);
//
//
//        UserEntity silvio = new UserEntity();
//        silvio.setFirstName("Silvio");
//        silvio.setLastName("Capobianco");
//        silvio.setEmail("silvio@test.com");
//        silvio.setGroupEntities(groups);
//        silvio.setUserId("silvioTeacher");
//        silvio.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        silvio.setRoles(List.of(roleTeacher));
//        userRepository.save(silvio);
//
//        UserGroupRoleEntity userGroupRoleEntity6 = new UserGroupRoleEntity();
//        userGroupRoleEntity6.setUser(silvio);
//        userGroupRoleEntity6.setGroupEntity(userGroup);
//        userGroupRoleEntity6.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity6);
//
//        UserEntity niccolo = new UserEntity();
//        niccolo.setFirstName("Niccolò");
//        niccolo.setLastName("Veltri");
//        niccolo.setGroupEntities(groups);
//        niccolo.setEmail("niccolo@test.com");
//        niccolo.setUserId("niccoloTeacher");
//        niccolo.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        niccolo.setRoles(List.of(roleTeacher));
//        userRepository.save(niccolo);
//
//        UserGroupRoleEntity userGroupRoleEntity7 = new UserGroupRoleEntity();
//        userGroupRoleEntity7.setUser(niccolo);
//        userGroupRoleEntity7.setGroupEntity(userGroup);
//        userGroupRoleEntity7.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity7);
//
//        UserEntity sadok = new UserEntity();
//        sadok.setFirstName("Sadok");
//        sadok.setLastName("BEN YAHIA");
//        sadok.setGroupEntities(groups);
//        sadok.setEmail("sadok@test.com");
//        sadok.setUserId("sadokTeacher");
//        sadok.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        sadok.setRoles(List.of(roleTeacher));
//        userRepository.save(sadok);
//
//        UserGroupRoleEntity userGroupRoleEntity8 = new UserGroupRoleEntity();
//        userGroupRoleEntity8.setUser(sadok);
//        userGroupRoleEntity8.setGroupEntity(userGroup);
//        userGroupRoleEntity8.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity8);
//
//        UserEntity juri = new UserEntity();
//        juri.setFirstName("Jüri");
//        juri.setLastName("Vain");
//        juri.setEmail("juri@test.com");
//        juri.setGroupEntities(groups);
//        juri.setUserId("juriTeacher");
//        juri.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        juri.setRoles(List.of(roleTeacher));
//        userRepository.save(juri);
//
//        UserGroupRoleEntity userGroupRoleEntity9 = new UserGroupRoleEntity();
//        userGroupRoleEntity9.setUser(juri);
//        userGroupRoleEntity9.setGroupEntity(userGroup);
//        userGroupRoleEntity9.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity9);
//
//        UserEntity sven = new UserEntity();
//        sven.setFirstName("Sven");
//        sven.setLastName("Nõmm");
//        sven.setEmail("sven@test.com");
//        sven.setGroupEntities(groups);
//        sven.setUserId("svenTeacher");
//        sven.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        sven.setRoles(List.of(roleTeacher));
//        userRepository.save(sven);
//
//        UserGroupRoleEntity userGroupRoleEntity10 = new UserGroupRoleEntity();
//        userGroupRoleEntity10.setUser(sven);
//        userGroupRoleEntity10.setGroupEntity(userGroup);
//        userGroupRoleEntity10.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity10);
//
//        UserEntity erki = new UserEntity();
//        erki.setFirstName("Erki");
//        erki.setLastName("Eessaar");
//        erki.setGroupEntities(groups);
//        erki.setEmail("erki@test.com");
//        erki.setUserId("erkiTeacher");
//        erki.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        erki.setRoles(List.of(roleTeacher));
//        userRepository.save(erki);
//
//        UserGroupRoleEntity userGroupRoleEntity11 = new UserGroupRoleEntity();
//        userGroupRoleEntity11.setUser(erki);
//        userGroupRoleEntity11.setGroupEntity(userGroup);
//        userGroupRoleEntity11.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity11);
//
//        UserEntity arvo = new UserEntity();
//        arvo.setFirstName("Arvo");
//        arvo.setLastName("Kaldmäe");
//        arvo.setGroupEntities(groups);
//        arvo.setEmail("arvo@test.com");
//        arvo.setUserId("arvoTeacher");
//        arvo.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        arvo.setRoles(List.of(roleTeacher));
//        userRepository.save(arvo);
//
//        UserGroupRoleEntity userGroupRoleEntity12 = new UserGroupRoleEntity();
//        userGroupRoleEntity12.setUser(arvo);
//        userGroupRoleEntity12.setGroupEntity(userGroup);
//        userGroupRoleEntity12.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity12);
//
//        UserEntity pawel = new UserEntity();
//        pawel.setFirstName("Pawel");
//        pawel.setLastName("Sobocinski");
//        pawel.setEmail("pawel@test.com");
//        pawel.setGroupEntities(groups);
//        pawel.setUserId("pawelTeacher");
//        pawel.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        pawel.setRoles(List.of(roleTeacher));
//        userRepository.save(pawel);
//
//        UserGroupRoleEntity userGroupRoleEntity13= new UserGroupRoleEntity();
//        userGroupRoleEntity13.setUser(pawel);
//        userGroupRoleEntity13.setGroupEntity(userGroup);
//        userGroupRoleEntity13.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity13);
//
//        UserEntity maksym = new UserEntity();
//        maksym.setFirstName("Maksym");
//        maksym.setLastName("Bortin");
//        maksym.setEmail("maksym@test.com");
//        maksym.setGroupEntities(groups);
//        maksym.setUserId("maksymTeacher");
//        maksym.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        maksym.setRoles(List.of(roleTeacher));
//        userRepository.save(maksym);
//
//        UserGroupRoleEntity userGroupRoleEntity14= new UserGroupRoleEntity();
//        userGroupRoleEntity14.setUser(maksym);
//        userGroupRoleEntity14.setGroupEntity(userGroup);
//        userGroupRoleEntity14.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity14);
//
//        UserEntity martin = new UserEntity();
//        martin.setFirstName("Martin");
//        martin.setLastName("Rebane");
//        martin.setEmail("martin@test.com");
//        martin.setGroupEntities(groups);
//        martin.setUserId("martinTeacher");
//        martin.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        martin.setRoles(List.of(roleTeacher));
//        userRepository.save(martin);
//
//        UserGroupRoleEntity userGroupRoleEntity15 = new UserGroupRoleEntity();
//        userGroupRoleEntity15.setUser(martin);
//        userGroupRoleEntity15.setGroupEntity(userGroup);
//        userGroupRoleEntity15.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity15);
//
//        UserEntity belikov = new UserEntity();
//        belikov.setFirstName("Juri");
//        belikov.setLastName("Belikov");
//        belikov.setEmail("belikov@test.com");
//        belikov.setGroupEntities(groups);
//        belikov.setUserId("belikovTeacher");
//        belikov.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        belikov.setRoles(List.of(roleTeacher));
//        userRepository.save(belikov);
//
//        UserGroupRoleEntity userGroupRoleEntity16 = new UserGroupRoleEntity();
//        userGroupRoleEntity16.setUser(belikov);
//        userGroupRoleEntity16.setGroupEntity(userGroup);
//        userGroupRoleEntity16.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity16);
//
//        UserEntity niels = new UserEntity();
//        niels.setFirstName("Niels");
//        niels.setLastName("Voorneveld");
//        niels.setEmail("niels@test.com");
//        niels.setUserId("nielsTeacher");
//        niels.setGroupEntities(groups);
//        niels.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        niels.setRoles(List.of(roleTeacher));
//        userRepository.save(niels);
//
//        UserGroupRoleEntity userGroupRoleEntity17 = new UserGroupRoleEntity();
//        userGroupRoleEntity17.setUser(niels);
//        userGroupRoleEntity17.setGroupEntity(userGroup);
//        userGroupRoleEntity17.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity17);
//
//        UserEntity edward = new UserEntity();
//        edward.setFirstName("Edward");
//        edward.setLastName("Morehouse");
//        edward.setEmail("edward@test.com");
//        edward.setGroupEntities(groups);
//        edward.setUserId("edwardTeacher");
//        edward.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        edward.setRoles(List.of(roleTeacher));
//        userRepository.save(edward);
//
//        UserGroupRoleEntity userGroupRoleEntity18 = new UserGroupRoleEntity();
//        userGroupRoleEntity18.setUser(edward);
//        userGroupRoleEntity18.setGroupEntity(userGroup);
//        userGroupRoleEntity18.setRole(roleTeacher);
//        userGroupRoleRepository.save(userGroupRoleEntity18);
//
//
//
//        ///////////////////////////////////////////
//        UserEntity student1 = new UserEntity();
//        student1.setFirstName("Jacob");
//        student1.setLastName("test");
//        student1.setEmail("jacob@test.com");
//        student1.setUserId("student1");
//        student1.setGroupEntities(groups);
//        student1.setGraduationLevel(UserEnum.GRADUATION_LEVEL_BACHELOR.getUserEnum());
//        student1.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        student1.setRoles(List.of(roleStudent));
//        userRepository.save(student1);
//
//        UserGroupRoleEntity userGroupRoleEntity20 = new UserGroupRoleEntity();
//        userGroupRoleEntity20.setUser(student1);
//        userGroupRoleEntity20.setGroupEntity(userGroup);
//        userGroupRoleEntity20.setRole(roleStudent);
//        userGroupRoleRepository.save(userGroupRoleEntity20);
//
//        UserEntity student2 = new UserEntity();
//        student2.setFirstName("Karl");
//        student2.setLastName("test");
//        student2.setEmail("karl@test.com");
//        student2.setUserId("student2");
//        student2.setGroupEntities(groups);
//        student2.setGraduationLevel(UserEnum.GRADUATION_LEVEL_BACHELOR.getUserEnum());
//        student2.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        student2.setRoles(List.of(roleStudent));
//        userRepository.save(student2);
//
//        UserGroupRoleEntity userGroupRoleEntity21 = new UserGroupRoleEntity();
//        userGroupRoleEntity21.setUser(student2);
//        userGroupRoleEntity21.setGroupEntity(userGroup);
//        userGroupRoleEntity21.setRole(roleStudent);
//        userGroupRoleRepository.save(userGroupRoleEntity21);
//
//        UserEntity student3 = new UserEntity();
//        student3.setFirstName("Toomas");
//        student3.setLastName("test");
//        student3.setEmail("toomas@test.com");
//        student3.setUserId("student3");
//        student3.setGraduationLevel(UserEnum.GRADUATION_LEVEL_BACHELOR.getUserEnum());
//        student3.setGroupEntities(groups);
//        student3.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
//        student3.setRoles(List.of(roleStudent));
//        userRepository.save(student3);
//
//        UserGroupRoleEntity userGroupRoleEntity22 = new UserGroupRoleEntity();
//        userGroupRoleEntity22.setUser(student3);
//        userGroupRoleEntity22.setGroupEntity(userGroup);
//        userGroupRoleEntity22.setRole(roleStudent);
//        userGroupRoleRepository.save(userGroupRoleEntity22);
//
//
//
//
//
//
//
//        TeamEntity testTeam1 = new TeamEntity();
//        testTeam1.setTeamName("TestTeam1");
//        testTeam1.setTeamId("testTeam1");
//        testTeam1.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeam1.setAuthorId(studentUser.getUserId());
//
//        TeamMemberEntity testTeamMember1 = new TeamMemberEntity();
//        testTeamMember1.setTeamMemberId("testTeamMember1");
//        testTeamMember1.setUser(studentUser);
//        testTeamMember1.setTeam(testTeam1);
//
//        studentUser.setStarterTeam(testTeam1.getTeamId());
//
//        teamRepository.save(testTeam1);
//        teamMemberRepository.save(testTeamMember1);
//        /////
//        TeamEntity testTeamJacob = new TeamEntity();
//        testTeamJacob.setTeamName("TestTeamJacob");
//        testTeamJacob.setTeamId("testTeamJacob");
//        testTeamJacob.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeamJacob.setAuthorId(student1.getUserId());
//
//        TeamMemberEntity testTeamMemberJacob = new TeamMemberEntity();
//        testTeamMemberJacob.setTeamMemberId("testTeamMemberJacob");
//        testTeamMemberJacob.setUser(student1);
//        testTeamMemberJacob.setTeam(testTeamJacob);
//
//        student1.setStarterTeam(testTeamJacob.getTeamId());
//        teamRepository.save(testTeamJacob);
//        teamMemberRepository.save(testTeamMemberJacob);
//        /////
//
//        /////
//        TeamEntity testTeamToomas = new TeamEntity();
//        testTeamToomas.setTeamName("TestTeamToomas");
//        testTeamToomas.setTeamId("testTeamToomas");
//        testTeamToomas.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeamToomas.setAuthorId(student3.getUserId());
//
//        TeamMemberEntity testTeamMemberToomas = new TeamMemberEntity();
//        testTeamMemberToomas.setTeamMemberId("testTeamMemberToomas");
//        testTeamMemberToomas.setUser(student3);
//        testTeamMemberToomas.setTeam(testTeamToomas);
//
//        student3.setStarterTeam(testTeamToomas.getTeamId());
//        teamRepository.save(testTeamToomas);
//        teamMemberRepository.save(testTeamMemberToomas);
//        /////
//
//        /////
//        TeamEntity testTeamKarl = new TeamEntity();
//        testTeamKarl.setTeamName("TestTeamKarl");
//        testTeamKarl.setTeamId("testTeamKarl");
//        testTeamKarl.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeamKarl.setAuthorId(student2.getUserId());
//
//        TeamMemberEntity testTeamMemberKarl = new TeamMemberEntity();
//        testTeamMemberKarl.setTeamMemberId("testTeamMemberKarl");
//        testTeamMemberKarl.setUser(student2);
//        testTeamMemberKarl.setTeam(testTeamKarl);
//
//        student2.setStarterTeam(testTeamKarl.getTeamId());
//        teamRepository.save(testTeamKarl);
//        teamMemberRepository.save(testTeamMemberKarl);
//        /////
//
//
//        TeamEntity testTeam2 = new TeamEntity();
//        testTeam2.setTeamName("TestTeam2");
//        testTeam2.setTeamId("testTeam2");
//        testTeam2.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeam2.setAuthorId(userEntity.getUserId());
//
//        TeamMemberEntity testTeamMember2 = new TeamMemberEntity();
//        testTeamMember2.setTeamMemberId("testTeamMember2");
//        testTeamMember2.setUser(userEntity);
//        testTeamMember2.setTeam(testTeam2);
//
//        userEntity.setStarterTeam(testTeam2.getTeamId());
//
//        teamRepository.save(testTeam2);
//        teamMemberRepository.save(testTeamMember2);
//
//
//        TeamEntity testTeam3 = new TeamEntity();
//        testTeam3.setTeamName("TestTeam3");
//        testTeam3.setTeamId("testTeam3");
//        testTeam3.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeam3.setAuthorId(teacherUser.getUserId());
//
//        TeamMemberEntity testTeamMember3 = new TeamMemberEntity();
//        testTeamMember3.setTeamMemberId("testTeamMember3");
//        testTeamMember3.setUser(teacherUser);
//        testTeamMember3.setTeam(testTeam3);
//
//        teacherUser.setStarterTeam(testTeam3.getTeamId());
//
//        teamRepository.save(testTeam3);
//        teamMemberRepository.save(testTeamMember3);
//
//
//        TeamEntity testTeam4 = new TeamEntity();
//        testTeam4.setTeamName("TestTeam4");
//        testTeam4.setTeamId("testTeam4");
//        testTeam4.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeam4.setAuthorId(userEntity2.getUserId());
//
//        TeamMemberEntity testTeamMember4 = new TeamMemberEntity();
//        testTeamMember4.setTeamMemberId("testTeamMember3");
//        testTeamMember4.setUser(userEntity2);
//        testTeamMember4.setTeam(testTeam4);
//
//        userEntity2.setStarterTeam(testTeam4.getTeamId());
//
//        teamRepository.save(testTeam4);
//        teamMemberRepository.save(testTeamMember4);
//
//
//        TeamEntity testTeam5 = new TeamEntity();
//        testTeam5.setTeamName("TestTeam5");
//        testTeam5.setTeamId("testTeam5");
//        testTeam5.setStatus(TeamEnum.STATUS_ACTIVE.getTeamEnum());
//        testTeam5.setAuthorId(userEntity1.getUserId());
//
//        TeamMemberEntity testTeamMember5 = new TeamMemberEntity();
//        testTeamMember5.setTeamMemberId("testTeamMember3");
//        testTeamMember5.setUser(userEntity1);
//        testTeamMember5.setTeam(testTeam5);
//
//        userEntity1.setStarterTeam(testTeam5.getTeamId());
//
//        teamRepository.save(testTeam5);
//        teamMemberRepository.save(testTeamMember5);
//
//
//
//        userRepository.save(teacherUser);
//        userRepository.save(userEntity);
//        userRepository.save(userEntity1);
//        userRepository.save(userEntity2);
//
//        ProjectEntity project = new ProjectEntity();
//        project.setAcceptingTime(new Date());
//        project.setCreatingTime(new Date());
//        project.setDegree(ProjectEnum.DEGREE_BACHELOR.getProjectEnum());
//        project.setDescription("some description to describe current project");
//        project.setLanguage("Estonian");
//        project.setProjectId("project1");
//        project.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
//        project.setStudentAmount(2);
//        project.setTitle("Graduation projects management service");
//        project.setUser(userRepository.findByUserId("testTeacher"));
//        projectRepository.save(project);
//
//        ProjectEntity project2 = new ProjectEntity();
//        project2.setAcceptingTime(new Date());
//        project2.setCreatingTime(new Date());
//        project2.setDegree(ProjectEnum.DEGREE_MASTER.getProjectEnum());
//        project2.setDescription("some description to describe current project");
//        project2.setLanguage("English");
//        project2.setProjectId("project2");
//        project2.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
//        project2.setStudentAmount(2);
//        project2.setTitle("ToDo list on React");
//        project2.setUser(userRepository.findByUserId("testTeacher"));
//        projectRepository.save(project2);
//
//        ProjectEntity project3 = new ProjectEntity();
//        project3.setAcceptingTime(new Date());
//        project3.setCreatingTime(new Date());
//        project3.setDegree(ProjectEnum.DEGREE_BACHELOR.getProjectEnum());
//        project3.setDescription("some description to describe current project");
//        project3.setLanguage("English");
//        project3.setProjectId("project3");
//        project3.setStatus(ProjectEnum.STATUS_AVAILABLE.getProjectEnum());
//        project3.setStudentAmount(2);
//        project3.setTitle("TalTech Bot for Students");
//        project3.setUser(userRepository.findByUserId("testTeacher"));
//        projectRepository.save(project3);
//
//    }
//
//    @Transactional
//    private AuthorityEntity createAuthority(String name){
//
//        AuthorityEntity authority = authorityRepository.findByName(name);
//        if (authority == null){
//            authority = new AuthorityEntity(name);
//            authorityRepository.save(authority);
//        }
//
//        return authority;
//
//    }
//
//    @Transactional
//    private RoleEntity createRole(String name, List<AuthorityEntity> authorities){
//
//        RoleEntity role = roleRepository.findByName(name);
//        if (role == null){
//            role = new RoleEntity(name);
//            role.setAuthorities(authorities);
//            roleRepository.save(role);
//        }
//
//        return role;



    }

    }
