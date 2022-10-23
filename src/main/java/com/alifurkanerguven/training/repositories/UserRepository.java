package com.alifurkanerguven.training.repositories;

import com.alifurkanerguven.training.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
