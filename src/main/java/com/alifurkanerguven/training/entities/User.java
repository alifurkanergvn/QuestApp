package com.alifurkanerguven.training.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //Bu bir entitiy class ıdır. Hibernateye bunu bir tabloya maple diyor
@Table(name = "user")
@Data  //Getter ve Setter larımızı otomatik olarak generate et.
public class User {

    @Id
    Long id;

    String userName;
    String password;

}
