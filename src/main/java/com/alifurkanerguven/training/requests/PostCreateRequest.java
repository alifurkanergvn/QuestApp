package com.alifurkanerguven.training.requests;

import lombok.Data;

@Data
//Bir Post un create edilebilmesi için gerekli olan istek elemanlarını barındırır.
public class PostCreateRequest {

    Long id;
    String text;
    String title;
    Long userId;
}
