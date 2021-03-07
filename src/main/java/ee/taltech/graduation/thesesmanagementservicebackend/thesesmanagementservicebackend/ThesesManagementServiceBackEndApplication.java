package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.security.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
	public CommandLineRunner initStarterPack(GroupRepository groupRepository) {
		return (args) -> {
//-----------------------------------------------> Group setting - SCHOOLS
			GroupEntity school2 = new GroupEntity();
			school2.setGroupClass("School");
			school2.setGroupName("School of Science");
			school2.setGroupId("school_science");
			groupRepository.save(school2);

			GroupEntity school3 = new GroupEntity();
			school3.setGroupClass("School");
			school3.setGroupName("School of Business and Governance");
			school3.setGroupId("school_business");
			groupRepository.save(school3);

			GroupEntity school = new GroupEntity();
			school.setGroupClass("School");
			school.setGroupName("School of Information Technologies");
			school.setGroupId("school_information_technologies");
			groupRepository.save(school);

			GroupEntity school1 = new GroupEntity();
			school1.setGroupClass("School");
			school1.setGroupName("School of Engineering");
			school1.setGroupId("school_engineering");
			groupRepository.save(school1);

			GroupEntity school4 = new GroupEntity();
			school4.setGroupClass("School");
			school4.setGroupName("Estonian Maritime Academy");
			school4.setGroupId("maritime_academy");
			groupRepository.save(school4);

//-----------------------------------------------> Group setting - DEPARTMENTS

			GroupEntity department = new GroupEntity();
			department.setGroupClass("Department");
			department.setGroupName("Informatics");
			department.setParentGroup(school);
			department.setGroupId("IAIB");

			GroupEntity department2 = new GroupEntity();
			department2.setGroupClass("Department");
			department2.setGroupName("Software Engineering");
			department2.setParentGroup(school);
			department2.setGroupId("IVSM");

			GroupEntity department3 = new GroupEntity();
			department3.setGroupClass("Department");
			department3.setGroupName("Computer Science");
			department3.setParentGroup(school);
			department3.setGroupId("IAPM");

			GroupEntity department4 = new GroupEntity();
			department4.setGroupClass("Department");
			department4.setGroupName("IT Systems Administration");
			department4.setParentGroup(school);
			department4.setGroupId("IAAB");

			GroupEntity savedSchool = groupRepository.findByGroupId("school_information_technologies");
			savedSchool.getSubGroups().add(department);
			savedSchool.getSubGroups().add(department2);
			savedSchool.getSubGroups().add(department3);
			savedSchool.getSubGroups().add(department4);
			groupRepository.save(savedSchool);
//------------------------------------------------------------->

			GroupEntity department5 = new GroupEntity();
			department5.setGroupClass("Department");
			department5.setGroupName("Applied Physics");
			department5.setParentGroup(school);
			department5.setGroupId("YAFM");

			GroupEntity department6 = new GroupEntity();
			department6.setGroupClass("Department");
			department6.setGroupName("FOOD Technology and Development");
			department6.setParentGroup(school);
			department6.setGroupId("KATM");


			GroupEntity savedSchool1 = groupRepository.findByGroupId("school_science");
			savedSchool1.getSubGroups().add(department5);
			savedSchool1.getSubGroups().add(department6);
			groupRepository.save(savedSchool1);


		};
	}
}
