package com.alifurkanerguven.training.controllers;

import com.alifurkanerguven.training.entities.User;
import com.alifurkanerguven.training.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser (@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //custom exeception ekle
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        //Update etmeden önce o user'i bulmak zorundasın
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = user.get();  //Optional olduğu için var olduğunu biliyorum o yüzden direkt .get dedik
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }

}
