package com.winfred.springbootblog.service.impl;

import com.winfred.springbootblog.exception.ResourceNotFoundException;
import com.winfred.springbootblog.model.Post;
import com.winfred.springbootblog.payload.PostDto;
import com.winfred.springbootblog.payload.PostResponse;
import com.winfred.springbootblog.repository.PostRepository;
import com.winfred.springbootblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> posts =postRepository.findAll(pageable);

        List<Post> listOfPosts=posts.getContent();

        List<PostDto> content= listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

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
        Post post = modelMapper.map(postDto, Post.class);

//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    //converting entity to DTO
    private PostDto mapToDto(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);

//        PostDto postDto = new PostDto();
////        postDto.setId(post.getId());
////        postDto.setTitle(post.getTitle());
////        postDto.setDescription(post.getDescription());
////        postDto.setContent(post.getContent());
        return postDto;

    }

}
