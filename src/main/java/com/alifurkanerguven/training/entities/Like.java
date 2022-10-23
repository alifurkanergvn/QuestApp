package com.alifurkanerguven.training.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "p_like")
@Data
public class Like {
    @Id
    Long id;
    //Bir çok Like'in tek bir Post ı olabilir.
    @ManyToOne(fetch = FetchType.LAZY)  //Ben Post objesini çektiğimde bana User objesini hemen çekme. Eğer EAGER yazsaydım Post'u çektiğimde direkt User verileride gelirdi
    @JoinColumn(name = "like_id", nullable = false)  //Post table ında user_id adında kolon oluşturup User objesinin id sine bagladigimi belirtir
    @OnDelete(action = OnDeleteAction.CASCADE)  //Bir User silindiğinde onun tüm Postları silinmeli
    @JsonIgnore //serialization kısmındaki hataları engeller bu alanı ignore et demiş olduk
    Post post;

    //Bir çok Like'in tek bir user ı olabilir.
    @ManyToOne(fetch = FetchType.LAZY)  //Ben Post objesini çektiğimde bana User objesini hemen çekme. Eğer EAGER yazsaydım Post'u çektiğimde direkt User verileride gelirdi
    @JoinColumn(name = "user_id", nullable = false)  //Post table ında user_id adında kolon oluşturup User objesinin id sine bagladigimi belirtir
    @OnDelete(action = OnDeleteAction.CASCADE)  //Bir User silindiğinde onun tüm Postları silinmeli
    @JsonIgnore //serialization kısmındaki hataları engeller bu alanı ignore et demiş olduk
    User user;

}
