package com.alifurkanerguven.training.repositories;

import com.alifurkanerguven.training.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserIdAndPostId(Long userId, Long longId); //Gelen userId ve longId lere göre tüm verileri dönecek

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);
}
