package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ThesesManagementServiceBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThesesManagementServiceBackEndApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/thesis/create").allowedOrigins("http://localhost:8080");
				registry.addMapping("/thesis/{id}").allowedOrigins("http://localhost:8080");


			}
		};
	}
}
