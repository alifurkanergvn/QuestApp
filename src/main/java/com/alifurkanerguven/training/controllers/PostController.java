package com.alifurkanerguven.training.controllers;

import com.alifurkanerguven.training.entities.Post;
import com.alifurkanerguven.training.requests.PostCreateRequest;
import com.alifurkanerguven.training.requests.PostUpdateRequest;
import com.alifurkanerguven.training.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // .../posts ile parametresiz olursa tüm postları,  .../posts?userId={userId} parametreli değer  olursa girilen id yi getir
    //@RequestParam requestin içerisindeki parametreleri parçalayıp alıyor
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    //@PathVariable direkt URL deki veriyi alıp içerisinde yazan değiskenin icerisine atar
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updateRequest){
        return postService.updateOnePostById(postId, updateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }

}
