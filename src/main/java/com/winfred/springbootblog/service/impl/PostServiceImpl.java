package com.winfred.springbootblog.service.impl;

import com.winfred.springbootblog.exception.ResourceNotFoundException;
import com.winfred.springbootblog.model.Post;
import com.winfred.springbootblog.payload.PostDto;
import com.winfred.springbootblog.repository.PostRepository;
import com.winfred.springbootblog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto create(PostDto postDto) {

        // converting DTO to entity
         Post post = mapToEntity(postDto);
         Post newPost = postRepository.save(post);

         //converting entity to DTO
         PostDto postResponse = mapToDto(newPost);
         return postResponse;
    }




    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts =postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {

        Post post = postRepository.findById(id).orElseThrow( () ->new ResourceNotFoundException("Post", "id", id));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
       Optional<Post> existingPost = Optional.ofNullable(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));

       Post updatingPost = existingPost.get();
       updatingPost.setTitle(postDto.getTitle());
       updatingPost.setContent(postDto.getContent());
       updatingPost.setDescription(postDto.getDescription());

       Post  updatedPost = postRepository.save(updatingPost);


       return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow( () ->new ResourceNotFoundException("Post", "id", id));

        postRepository.delete(post);

    }


    // converting DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    //converting entity to DTO
    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;

    }

}
