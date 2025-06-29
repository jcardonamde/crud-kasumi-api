package com.kasumi.crud_kasumi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CrudKasumiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudKasumiApplication.class, args);
	}
}
