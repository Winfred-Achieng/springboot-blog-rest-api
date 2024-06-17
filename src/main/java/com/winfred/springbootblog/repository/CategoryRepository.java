package com.winfred.springbootblog.repository;

import com.winfred.springbootblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
