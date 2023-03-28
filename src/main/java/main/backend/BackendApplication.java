package main.backend;

import main.backend.services.UsersService;
import main.backend.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {

		SpringApplication.run(BackendApplication.class, args);
	}

}
