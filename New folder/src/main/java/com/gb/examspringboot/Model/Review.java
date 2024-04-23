package com.gb.examspringboot.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Review {
    private int id;
    private String authorName;
    private String authorAva;
    private String content;
    private int rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    LocalDate createdAt;

}
