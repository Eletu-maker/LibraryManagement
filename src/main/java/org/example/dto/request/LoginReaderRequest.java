package org.example.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginReaderRequest {
    @Email(message = "invalid email")
    private String email;

    @Size(min = 6, max = 20, message = "password must be at least 6 characters")
    private String password;
}
