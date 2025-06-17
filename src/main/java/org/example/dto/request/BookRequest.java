package org.example.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "please enter your title")
    private String title;
    @NotBlank(message = "please enter your name")
    private String author;
    @Size(min = 2,  message = "books must be more than 1")
    private int number;

}
