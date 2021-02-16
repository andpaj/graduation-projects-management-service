package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.DepartmentEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.UserEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.DepartmentRepository;
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
	public CommandLineRunner initStarterPack(DepartmentRepository departmentRepository, UserRepository userRepository) {
		return (args) -> {

			DepartmentEntity departmentEntity = new DepartmentEntity();
			departmentEntity.setDepartmentId("testId");
			departmentEntity.setDepartmentName("computer");
			departmentRepository.save(departmentEntity);

			UserEntity userEntity = new UserEntity();
			userEntity.setUserId("testId");
			userEntity.setFirstName("testFirstName");
			userEntity.setLastName("testLastName");
			userEntity.setEmail("test@test.com");
			userEntity.setEncryptedPassword("$2a$10$8ufbSYN8I/mwsVk6HffOiuJiQykbTjiuG7cwEDxxdY9K7NDS0Y2km");
			userEntity.setDepartment(departmentEntity);
			userRepository.save(userEntity);


		};
	}
}
