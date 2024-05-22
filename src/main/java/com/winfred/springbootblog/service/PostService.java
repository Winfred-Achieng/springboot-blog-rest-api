package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.PostDto;

public interface PostService {

    PostDto create(PostDto postDto);
}
