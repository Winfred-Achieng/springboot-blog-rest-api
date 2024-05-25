package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto create(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long Id);

    PostDto updatePost(PostDto postDto,long id);

    void deletePostById(long Id);
}
