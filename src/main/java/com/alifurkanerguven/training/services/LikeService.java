package com.alifurkanerguven.training.services;

import com.alifurkanerguven.training.entities.Comment;
import com.alifurkanerguven.training.entities.Like;
import com.alifurkanerguven.training.entities.Post;
import com.alifurkanerguven.training.entities.User;
import com.alifurkanerguven.training.repositories.LikeRepository;
import com.alifurkanerguven.training.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }else
            return likeRepository.findAll();
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User oneUserById = userService.getOneUserById(likeCreateRequest.getUserId());
        Post onePostById = postService.getOnePostById(likeCreateRequest.getPostId());
        if (onePostById != null && oneUserById != null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(oneUserById);
            likeToSave.setPost(onePostById);
            return likeRepository.save(likeToSave);
        }
        return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
