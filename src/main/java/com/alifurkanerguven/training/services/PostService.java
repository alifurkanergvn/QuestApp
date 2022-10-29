package com.alifurkanerguven.training.services;

import com.alifurkanerguven.training.entities.Post;
import com.alifurkanerguven.training.entities.User;
import com.alifurkanerguven.training.repositories.PostRepository;
import com.alifurkanerguven.training.requests.PostCreateRequest;
import com.alifurkanerguven.training.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }
    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        //Post create etmemiz için o Postun User ı var mı onu teyit etmek zorundayız
        //Post obejsinin User obejesi Null olmaması gerekiyor çunku nullable=false
        User oneUser = userService.getOneUser(newPostRequest.getUserId());
        if (oneUser==null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(oneUser); //Database den getirdiğimiz User ı kullandık
        return postRepository.save(toSave);
    }


    public Post updateOnePostById(Long postId, PostUpdateRequest updateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()){
            return null;
        }else{
            Post toUpdate = post.get();  //Controllerdan gelen postu get yaptık
            toUpdate.setTitle(updateRequest.getTitle());
            toUpdate.setText(updateRequest.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        }

    }


    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

}
