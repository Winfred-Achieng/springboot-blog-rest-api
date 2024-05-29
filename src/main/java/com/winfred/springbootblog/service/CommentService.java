package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
}
