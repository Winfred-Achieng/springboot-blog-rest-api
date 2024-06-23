package com.winfred.springbootblog.controller;

import com.winfred.springbootblog.payload.CommentDto;
import com.winfred.springbootblog.service.CommentService;
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
@RequestMapping("/api/")
@Tag( name ="CRUD REST APIs for Comment Resources " )

public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @Operation( summary = "Create Commment REST API",
            description = "Create Comment REST API is used to save comment into database")

    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value ="postId") long postId, @Valid @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    @Operation( summary = "Get All Comments By Post Id REST API",
            description = "Get All Comments By Post Id REST API is used to fetch all comments by a Post Id from the database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 2010 SUCCESS")

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId (@PathVariable(value = "postId")long postId){

        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),HttpStatus.OK);
    }


    @Operation( summary = "Get Comment By Id REST API",
            description = "Get Comment By Id REST API is used to get single comment from the database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 2010 SUCCESS")

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId")long postId, @PathVariable(value = "id")long commentId){
        CommentDto commentDto =commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }


    @Operation( summary = "Update Comment REST API",
            description = "Update Comment REST API is used to update a particular comment in a database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId")long postId, @PathVariable(value = "id") long commentId,@Valid @RequestBody CommentDto commentRequest){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentRequest);

        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }


    @Operation( summary = "Delete Comment REST API",
            description = "Delete Comment REST API is used to delete a particular comment in a database")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId")long postId, @PathVariable(value = "id")long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}
