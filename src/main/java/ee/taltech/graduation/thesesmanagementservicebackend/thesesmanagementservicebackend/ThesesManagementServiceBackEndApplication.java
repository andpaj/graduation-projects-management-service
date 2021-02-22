package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.DepartmentRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.UserRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.security.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class ThesesManagementServiceBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThesesManagementServiceBackEndApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties(){
		return new AppProperties();
	}

	@Bean
	public CommandLineRunner initStarterPack(DepartmentRepository departmentRepository,
											 UserRepository userRepository,
											 GroupRepository groupRepository) {
		return (args) -> {
//-----------------------------------------------> Group setting
			GroupEntity school = new GroupEntity();
			school.setGroupClass("School");
			school.setGroupName("Computer Science");
			school.setGroupId("test");
			groupRepository.save(school);

			GroupEntity department = new GroupEntity();
			department.setGroupClass("Department");
			department.setGroupName("Math");
			department.setParentGroup(school);
			department.setGroupId("test1");

			GroupEntity department2 = new GroupEntity();
			department2.setGroupClass("Department");
			department2.setGroupName("Chem");
			department2.setParentGroup(school);
			department2.setGroupId("test2");

			GroupEntity savedSchool = groupRepository.findByGroupName("Computer Science");
			savedSchool.getSubGroups().add(department);
			savedSchool.getSubGroups().add(department2);
			groupRepository.save(savedSchool);

//------------------------------------------------------------->
			DepartmentEntity departmentEntity = new DepartmentEntity();
			departmentEntity.setDepartmentId("testId");
			departmentEntity.setDepartmentName("computer");
			departmentRepository.save(departmentEntity);

			UserEntity userEntity = new UserEntity();
			userEntity.setUserId("testId");
			userEntity.setFirstName("testCI/CD");
			userEntity.setLastName("testCI/CD");
			userEntity.setEmail("test@test.com");
			userEntity.setEncryptedPassword("$2a$10$8ufbSYN8I/mwsVk6HffOiuJiQykbTjiuG7cwEDxxdY9K7NDS0Y2km");
			userEntity.setDepartment(departmentEntity);

			GroupEntity userGroup = groupRepository.findByGroupName("Math");
			userEntity.setGroupEntity(userGroup);
			userGroup.getUsers().add(userEntity);

			userRepository.save(userEntity);



		};
	}
}
