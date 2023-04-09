package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Query(value = "Select * FROM books  WHERE id = ?1", nativeQuery = true)
    Book _findById(Long id);

    @Query(value = "SELECT DISTINCT book.* FROM books book INNER JOIN book_categories t2 " +
            "ON book.id = t2.book_id " +
            "INNER JOIN review re ON re.book_id = book.id WHERE t2.category_id in ?1" +
            " AND book.language in ?2" +
            " HAVING AVG(re.value) >= ?3 ", nativeQuery = true)
    ArrayList<Book> findAllBookByCategoriesAndReviewAndLanguages( List<Long> categories,
                                             List<String> languages,
                                             int value);

    @Query(value = "SELECT DISTINCT book.* FROM books book INNER JOIN book_categories t2 " +
            "ON book.id = t2.book_id " +
            "WHERE t2.category_id in ?1" +
            " AND book.language in ?2",
            nativeQuery = true)
    ArrayList<Book> findAllBookByCategories( List<Long> categories,
                                                                  List<String> languages
                                                                  );



}
