package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Book;
import com.dev0kch.mybook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "Select * FROM users  WHERE id = ?1", nativeQuery = true)
    User _findById(Long id);
}
