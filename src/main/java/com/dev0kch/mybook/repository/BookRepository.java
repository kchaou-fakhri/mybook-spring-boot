package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "Select * FROM books  WHERE id = ?1", nativeQuery = true)
    Book _findById(Long id);

    @Query(value = "SELECT DISTINCT * FROM books book INNER JOIN book_categories t2 " +
            "ON book.id = t2.book_id " +
            "INNER JOIN review re ON re.book_id = book.id WHERE t2.category_id in ?1" +
            " AND book.language in ?2" +
            " GROUP BY book.id HAVING AVG(re.value) >= ?3 ", nativeQuery = true)
    ArrayList<Book> findAllBookByCategories( List<Long> categories,
                                             List<String> languages,
                                             int value);



}
