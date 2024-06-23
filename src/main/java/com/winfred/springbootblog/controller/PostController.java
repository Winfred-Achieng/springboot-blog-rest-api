package com.winfred.springbootblog.controller;


import com.winfred.springbootblog.payload.PostDto;
import com.winfred.springbootblog.payload.PostResponse;
import com.winfred.springbootblog.service.PostService;
import com.winfred.springbootblog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Tag( name ="CRUD REST APIs for Post Resources " )

public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation( summary = "Create Post REST API",
    description = "Create Post REST API is used to save post into database")

    @ApiResponse(responseCode = "201",
    description = "Http Status 201 CREATED")


    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto ){

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }



    @Operation( summary = "Get All Post REST API",
            description = "Get All Post REST API is used to fetch all the post from the database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(@RequestParam (value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                    @RequestParam (value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam (value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam (value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }




    @Operation( summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 2010 SUCCESS")

    @GetMapping(value = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }



    @Operation( summary = "Update Post REST API",
            description = "Update Post REST API is used to update a particular post in a database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id){

        PostDto updatedPost = postService.updatePost(postDto,id);

        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


    @Operation( summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post in a database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")


    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully",HttpStatus.OK);
    }



    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") Long categoryId){

        return new ResponseEntity<>(postService.getPostsByCategory(categoryId),HttpStatus.OK);
    }



}
