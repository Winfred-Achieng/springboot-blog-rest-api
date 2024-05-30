package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId,long commentId);


}
