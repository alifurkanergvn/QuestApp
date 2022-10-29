package com.alifurkanerguven.training.repositories;

import com.alifurkanerguven.training.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //Burada findBy bir kalıptır. Bundan sonra objenin içerisinde yer alan=<Post>, tüm field larla kendi sorgunu olusturabilirsin gerisini JPA hallediyor
    List<Post> findByUserId(Long userId); //parametrede verdiğim userId a sahip tüm postları dönecektir.
}
