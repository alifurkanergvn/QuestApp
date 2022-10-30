package com.alifurkanerguven.training.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
    Long id;
    Long postId;
    Long userId;
}
