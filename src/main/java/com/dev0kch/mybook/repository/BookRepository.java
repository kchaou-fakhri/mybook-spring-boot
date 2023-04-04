package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "Select * FROM books  WHERE id = ?1", nativeQuery = true)
    Book _findById(int id);

    @Query(value = "SELECT * FROM books book INNER JOIN book_categories t2 ON book.id = t2.book_id WHERE t2.category_id = ?1", nativeQuery = true)
    List<Book> findAllBookByCategories(long categoryId);



}
