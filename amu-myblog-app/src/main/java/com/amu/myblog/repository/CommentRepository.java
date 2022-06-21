package com.amu.myblog.repository;

import com.amu.myblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Created 2022/06/21
 * @Author Amu
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
