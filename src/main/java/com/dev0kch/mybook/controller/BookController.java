package com.dev0kch.mybook.controller;



import com.dev0kch.mybook.model.Book;
import com.dev0kch.mybook.model.Category;
import com.dev0kch.mybook.model.Filter;
import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.BookRepository;
import com.dev0kch.mybook.repository.CategoryRepository;
import com.dev0kch.mybook.repository.ReviewRepository;
import com.dev0kch.mybook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("book/{id}")
    public Book findById(@PathVariable Long id){
        return bookRepository._findById(id);
    }

    @PostMapping(value = "book/save/{category_id}")
    public Book createBook(@RequestBody Book book, @PathVariable(name = "category_id") Long category_id)
    {
        Category category = categoryRepository._findById(category_id);
        Book _book = book;

        _book.addCategory(category);
        return bookRepository.save(_book);
    }

    @PutMapping("book/add_categories/{book_id}/{category_id}")
    public Book addCategories(  @PathVariable(name = "book_id") Long book_id, @PathVariable(name = "category_id") Long category_id){
        Book book = bookRepository._findById(book_id);
        Category category = categoryRepository._findById(category_id);
        book.addCategory(category);
        return bookRepository.save(book);
    }

    @PostMapping(value = "book/saveall")
    public void saveAllBook(@RequestBody List<Book> books)
    {
        bookRepository.saveAll(books);
    }

    @GetMapping("/books/{page}")
    public List<Book> findAll(@PathVariable("page") int page){
        ArrayList<Book> books = new ArrayList<>();
        Pageable paging = PageRequest.of(
                page, 25, Sort.by("id").ascending());
        for (Book book : bookRepository.findAll(paging)){
            book.setReview(reviewRepository.getReviewByBook(book.getId()));
            books.add(book);
        }
        return books;
    }

    @DeleteMapping("book/delete/{id}")
    public void delete(@PathVariable Long id){
        bookRepository.deleteById(id);
    }

    @PostMapping("book/by_all")
    public List<Book> findAllBookByCategoriesAndReviewAndLanguages(@RequestBody Filter filter){

        ArrayList<Long> arrayCategoriesId = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();




        if (filter.getLanguages().size()==0){
            filter.setLanguages(List.of("ar","fr","sp","en"));
        }
        if(filter.getCategories().size()==0){

            for (Category category : categories){
                arrayCategoriesId.add(category.getId());
            }

        }
        else {
            for (String categoryName : filter.getCategories()){
                for (Category category : categories){
                    if (categoryName.equals(category.getCategoryName())){
                        arrayCategoriesId.add(category.getId());
                        break;
                    }
                }
            }
        }


        // Check if review > 0 to filter with review else filter without review
        List<Book> books = new ArrayList<>() ;
        if(filter.getReview() > 0){


            for (Book book : bookRepository.
                    findAllBookByCategoriesAndReviewAndLanguages(arrayCategoriesId,
                            filter.getLanguages(), filter.getReview(), filter.getPrice())){

                    book.setReview(reviewRepository.getReviewByBook(book.getId()));
                    books.add(book);

            }
        }
        else {

            for (Book book : bookRepository.findAllBookByCategories(arrayCategoriesId, filter.getLanguages(), filter.getPrice())){

                    book.setReview(reviewRepository.getReviewByBook(book.getId()));
                    books.add(book);

            }
        }


        return books;
    }


    @PutMapping("book/buy/{book_id}/{user_id}")
    public Book buyBook(  @PathVariable(name = "book_id") Long book_id, @PathVariable(name = "user_id") Long user_id){
        Book book = bookRepository._findById(book_id);
        User user = userRepository._findById(user_id);
        book.buyBook(user);
        return bookRepository.save(book);
    }


    @PostMapping("book/is_sold/{book_id}/{user_id}")
    public String isSold(  @PathVariable(name = "book_id") Long book_id , @PathVariable(name = "user_id") Long user_id){
        String result = "{ \"isSold\" : false}";
        try {
            ResultSet res = bookRepository.isSold(book_id, user_id);
            if (res != null){
                result = "{ \"isSold\" : true}";
            }
        }
        catch (Exception e){

        }

        return result;
    }


}
