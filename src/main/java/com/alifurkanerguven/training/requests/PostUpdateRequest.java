package com.alifurkanerguven.training.requests;

import lombok.Data;

//Bir postu guncellerken onun ıd'lerini güncellemezsin sadece title ike text ini güncellersin
@Data
public class PostUpdateRequest {
    String title;
    String text;

}
