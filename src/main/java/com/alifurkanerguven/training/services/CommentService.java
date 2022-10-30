package com.alifurkanerguven.training.services;

import com.alifurkanerguven.training.entities.Comment;
import com.alifurkanerguven.training.entities.Post;
import com.alifurkanerguven.training.entities.User;
import com.alifurkanerguven.training.repositories.CommentRepository;
import com.alifurkanerguven.training.requests.CommentCreateRequest;
import com.alifurkanerguven.training.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }else
            return commentRepository.findAll();
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User oneUserById = userService.getOneUserById(commentCreateRequest.getUserId()); //Gelen user var mı yok mu teyit ediyoruz
        Post onePostById = postService.getOnePostById(commentCreateRequest.getPostId());
        if (oneUserById != null && onePostById != null){
            Comment CommentToSave = new Comment();
            CommentToSave.setId(commentCreateRequest.getId());
            CommentToSave.setText(commentCreateRequest.getText());
            CommentToSave.setUser(oneUserById);
            CommentToSave.setPost(onePostById);
            return commentRepository.save(CommentToSave);
        }
        return null;
    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            commentRepository.save(commentToUpdate);
            return commentToUpdate;
        }else
            return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
