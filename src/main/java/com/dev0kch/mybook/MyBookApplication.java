package com.dev0kch.mybook;

import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.UserRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableEncryptableProperties
public class MyBookApplication {


	public static void main(String[] args) {
		SpringApplication.run(MyBookApplication.class, args);
	}

}
