package com.dev0kch.mybook.controller;

import com.dev0kch.mybook.model.Book;
import com.dev0kch.mybook.model.Review;
import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.BookRepository;
import com.dev0kch.mybook.repository.ReviewRepository;
import com.dev0kch.mybook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("review/add/{review}/{user_id}/{book_id}")
    public Review addReview(@PathVariable("review") int review,
                            @PathVariable("user_id") Long user_id,
                            @PathVariable("book_id") Long book_id){

        User user = userRepository._findById(user_id);
        Book book = bookRepository._findById(book_id);
        String _id = user.getUsername()+user_id+""+book_id;

        return reviewRepository.save(new Review(_id ,review,book, user));
    }

    @GetMapping("review/by_book/{book_id}")
    Double getReview(@PathVariable("book_id") Long book_id){
        return reviewRepository.getReviewByBook(book_id);
    }

}
