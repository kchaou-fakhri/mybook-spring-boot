package com.dev0kch.mybook.repository;

import com.dev0kch.mybook.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {


    @Query(value = "SELECT AVG(value) from review where book_id =?1" , nativeQuery = true)
    Double getReviewByBook(Long id);
}
