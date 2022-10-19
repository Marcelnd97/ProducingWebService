package com.exam.examInLine.controller;

import com.exam.examInLine.model.exam.Category;
import com.exam.examInLine.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Ajout catégory

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    //recuperation des categories
    @GetMapping("/{categoryId}")
        public Category getCategory(@PathVariable("categoryId") Long categoryId) {
            return this.categoryService.getCategory(categoryId);
        }

     // Récupérer tous les categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //Modification des Categories
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory((categoryId));
    }
}
