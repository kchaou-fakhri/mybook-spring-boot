package com.dev0kch.mybook.controller;



import com.dev0kch.mybook.model.Book;
import com.dev0kch.mybook.model.Category;
import com.dev0kch.mybook.model.Filter;
import com.dev0kch.mybook.repository.BookRepository;
import com.dev0kch.mybook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("book/{id}")
    public Book findById(@PathVariable int id){
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
    public Book addCategories(  @PathVariable(name = "book_id") int book_id, @PathVariable(name = "category_id") Long category_id){
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

    @GetMapping("/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @DeleteMapping("book/delete/{id}")
    public void delete(@PathVariable int id){
        bookRepository.deleteById(id);
    }

    @GetMapping("book/by_category")
    public List<Book> findAllBookByCategories(@RequestBody Filter filter){


        List<Book> books = new ArrayList<>() ;

        for (Book book : bookRepository.findAllBookByCategories(filter.getCategories(), filter.getLanguages())){
            if (!books.contains(book)){
                books.add(book);
            }
        }

        return books;
    }

}
