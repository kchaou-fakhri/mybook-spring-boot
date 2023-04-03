package com.dev0kch.mybook.controller;

import com.dev0kch.mybook.model.Category;
import com.dev0kch.mybook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("category/create")
    public void createCategory(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @PostMapping("category/createall")
    public void createCategory(@RequestBody List<Category> categories){
        categoryRepository.saveAll(categories);
    }

    @DeleteMapping("category/delete/{id}")
    public void deleteCategory(@PathVariable(name = "id") Long id){
        categoryRepository.deleteById(id);
    }

    @GetMapping("category/{id}")
    public Category getCategoryById(@PathVariable(name = "id") Long id){
       return categoryRepository._findById(id);
    }

    @GetMapping("category/all")
    public List<Category> getCategoryAll(){
        return categoryRepository.findAll();
    }
}
