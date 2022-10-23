package com.alifurkanerguven.training.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    Long id;

    //Long userId; mapping yapacağız User a o sebeple Bu satırı User user; olarak degistiriyorum
    //Bir çok postun tek bir user ı olabilir.
    @ManyToOne(fetch = FetchType.LAZY)  //Ben Post objesini çektiğimde bana User objesini hemen çekme. Eğer EAGER yazsaydım Post'u çektiğimde direkt User verileride gelirdi
    @JoinColumn(name = "user_id", nullable = false)  //Post table ında user_id adında kolon oluşturup User objesinin id sine bagladigimi belirtir
    @OnDelete(action = OnDeleteAction.CASCADE)  //Bir User silindiğinde onun tüm Postları silinmeli
    @JsonIgnore //serialization kısmındaki hataları engeller bu alanı ignore et demiş olduk
    User user;


    String title;
    @Lob
    @Column(columnDefinition = "text")  //Hibernate'in mySql de String i text olarak algılamsı için yazıldı. Yoksa varchar 255 olarka alır
    String text;

}
