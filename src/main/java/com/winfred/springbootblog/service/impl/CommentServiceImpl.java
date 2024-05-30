package com.winfred.springbootblog.service.impl;


import com.winfred.springbootblog.exception.BlogApiException;
import com.winfred.springbootblog.exception.ResourceNotFoundException;
import com.winfred.springbootblog.model.Comment;
import com.winfred.springbootblog.model.Post;
import com.winfred.springbootblog.payload.CommentDto;
import com.winfred.springbootblog.repository.CommentRepository;
import com.winfred.springbootblog.repository.PostRepository;
import com.winfred.springbootblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        //set post to comment entity
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        /* retrieve comments by postId */
        List<Comment> comments = commentRepository.findByPostId(postId);

        //convert list of comment entities to list of comment dto's
        return comments.stream().map(comment ->mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrieve post entity by id
       Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

       //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {

            throw new BlogApiException(HttpStatus.BAD_REQUEST," comment does not belong to a post");
        }

        return mapToDto(comment);
    }


    //Converting Entity to DTO
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    //Converting Dto to Entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
