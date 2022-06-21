package com.amu.myblog.repository;

import com.amu.myblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created 2022/06/17
 * @Author Amu
 */
public interface PostRepository extends JpaRepository<Post,Long>{
}
