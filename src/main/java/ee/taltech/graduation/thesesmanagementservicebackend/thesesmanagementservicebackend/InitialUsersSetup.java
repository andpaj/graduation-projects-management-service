package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.AuthorityEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.RoleEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.AuthorityRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.RoleRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Roles;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

//Class which called after application started
@Component
public class InitialUsersSetup {

    @Autowired
    UserRepository userRepository;

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
        adminUser.setEmail("testAdmin@test.com");
        adminUser.setUserId("testAdmin");
        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        adminUser.setRole(roleAdmin);

        userRepository.save(adminUser);

        UserEntity studentUser = new UserEntity();
        studentUser.setFirstName("Student");
        studentUser.setLastName("Test");
        studentUser.setEmail("testStudent@test.com");
        studentUser.setUserId("testStudent");
        studentUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        studentUser.setRole(roleStudent);

        userRepository.save(studentUser);

        UserEntity teacherUser = new UserEntity();
        teacherUser.setFirstName("Teacher");
        teacherUser.setLastName("Test");
        teacherUser.setEmail("testTeacher@test.com");
        teacherUser.setUserId("testTeacher");
        teacherUser.setEncryptedPassword(bCryptPasswordEncoder.encode("test"));
        teacherUser.setRole(roleTeacher);

        userRepository.save(teacherUser);

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
    private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities){

        RoleEntity role = roleRepository.findByName(name);
        if (role == null){
            role = new RoleEntity(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }

        return role;



    }


}
