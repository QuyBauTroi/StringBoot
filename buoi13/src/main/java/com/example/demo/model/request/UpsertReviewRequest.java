package com.example.demo.model.request;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertReviewRequest {
    String content;
    Integer rating;
    Integer movieId;
}


