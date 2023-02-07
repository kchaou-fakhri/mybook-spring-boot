package com.dev0kch.mybook;

import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.UserRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEncryptableProperties
public class MyBookApplication {
	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initUsers() {
		List<User> users = Stream.of(
				new User(101L, "javatechie", "password", "javatechie@gmail.com"),
				new User(102L, "user1", "pwd1", "user1@gmail.com"),
				new User(103L, "user2", "pwd2", "user2@gmail.com"),
				new User(104L, "user3", "pwd3", "user3@gmail.com")
		).collect(Collectors.toList());
		repository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(MyBookApplication.class, args);
	}

}
