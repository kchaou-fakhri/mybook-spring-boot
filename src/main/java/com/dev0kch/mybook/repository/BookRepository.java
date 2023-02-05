package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "Select * FROM books  WHERE id = ?1", nativeQuery = true)
    Book _findById(Long id);

}
