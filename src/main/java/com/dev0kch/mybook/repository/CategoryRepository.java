package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Book;
import com.dev0kch.mybook.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "Select * FROM category  WHERE id = ?1", nativeQuery = true)
    Category _findById(Long id);
}
