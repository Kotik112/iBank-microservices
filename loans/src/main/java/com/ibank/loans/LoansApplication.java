package com.ibank.loans;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Loans Management API",
				version = "1.0",
				description = "Documentation Loans Management API v1.0",
				contact = @Contact(
						name = "Arman Iqbal",
						email = "armaniqbal@gmail.com",
						url = "https://www.linkedin.com/in/arman-iqbal-39803681/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0"
				)
		)

)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
