package com.winfred.springbootblog.controller;

import com.winfred.springbootblog.payload.CategoryDto;
import com.winfred.springbootblog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long id){

        CategoryDto category = categoryService.getCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryDtoList);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable(name = "id") Long categoryId){
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("{id}")

    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id){
        categoryService.deleteCategory(id);

        return new ResponseEntity<>("Category Deleted", HttpStatus.OK);

    }


}
