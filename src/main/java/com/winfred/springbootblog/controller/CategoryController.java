package com.winfred.springbootblog.controller;

import com.winfred.springbootblog.payload.CategoryDto;
import com.winfred.springbootblog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag( name ="CRUD REST APIs for Category Resources " )

public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Operation( summary = "Create Category REST API",
            description = "Create Category REST API is used to add category into database")

    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }


    @Operation( summary = "Get Category By Id REST API",
            description = "Get Category By Id REST API is used to get single category from the database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 2010 SUCCESS")

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long id){

        CategoryDto category = categoryService.getCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @Operation( summary = "Get All Categories REST API",
            description = "Get All Categories REST API is used to fetch all the categories from the database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryDtoList);
    }

    @Operation( summary = "Update Category REST API",
            description = "Update Category REST API is used to update a particular category in a database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable(name = "id") Long categoryId){
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @Operation( summary = "Delete Category REST API",
            description = "Delete Category REST API is used to delete a particular category in a database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id){
        categoryService.deleteCategory(id);

        return new ResponseEntity<>("Category deleted", HttpStatus.OK);

    }


}
