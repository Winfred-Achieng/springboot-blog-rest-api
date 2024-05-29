package com.winfred.springbootblog.repository;

import com.winfred.springbootblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface CommentRepository extends JpaRepository<Comment, Long> {
}
