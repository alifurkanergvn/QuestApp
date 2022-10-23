package com.alifurkanerguven.training.services;

import com.alifurkanerguven.training.entities.User;
import com.alifurkanerguven.training.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }


    public User updateOneUser(Long userId, User newUser) {
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

    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
