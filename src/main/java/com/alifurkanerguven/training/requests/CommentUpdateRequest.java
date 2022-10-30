package com.alifurkanerguven.training.requests;

import lombok.Data;

@Data
public class CommentUpdateRequest {
    //id,postId,userId yi update etmeyeceğime göre sadece text'i update edebilirim
    String text;
}
