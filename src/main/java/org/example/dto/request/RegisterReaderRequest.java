package org.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class RegisterReaderRequest {
    @NotBlank(message = "please enter your name")
    private String name;

    @Email(message = "invalid email")
    private String email;


   @Size(min = 6, max = 20, message = "password must be at least 6 characters")
    private String password;

    @NotBlank(message = "enter a valid phone number")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Invalid phone number format"
)
    private String phoneNumber;
}
