package net.javaguides.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Configuration
@ComponentScan(basePackages = "net.javaguides.*")
@EntityScan("net.javaguides.*")

	public class BookstoreApplication {

		public static void main(String[] args) {

			SpringApplication.run(BookstoreApplication.class, args);
		}

	}
