package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.PostDto;
import com.winfred.springbootblog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto create(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long Id);

    PostDto updatePost(PostDto postDto,long id);

    void deletePostById(long Id);
}
