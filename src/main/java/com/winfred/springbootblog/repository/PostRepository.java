package com.winfred.springbootblog.repository;

import com.winfred.springbootblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface PostRepository extends JpaRepository<Post, Long> {


}
