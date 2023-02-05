package com.dev0kch.mybook.controller;



import com.dev0kch.mybook.model.Book;
import com.dev0kch.mybook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("book/{id}")
    public Book findById(@PathVariable Long id){
        return bookRepository._findById(id);
    }

    @PutMapping(value = "book/save")
    public void updateBook(@RequestBody Book book)
    {
        bookRepository.save(book);
    }

    @GetMapping("/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @DeleteMapping("book/delete/{id}")
    public void delete(@PathVariable int id){
        bookRepository.deleteById(id);
    }

}
