package com.winfred.springbootblog.controller;


import com.winfred.springbootblog.payload.PostDto;
import com.winfred.springbootblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts(@RequestParam (value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                     @RequestParam (value = "pageSize", defaultValue = "10",required = false) int pageSize){
        return postService.getAllPosts(pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id){

        PostDto updatedPost = postService.updatePost(postDto,id);

        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
        postService.deletePostById(id);

        return new ResponseEntity<>("Post deleted successfully",HttpStatus.OK);
    }
}
