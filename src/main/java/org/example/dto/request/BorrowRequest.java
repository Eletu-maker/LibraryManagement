package org.example.dto.request;

import lombok.Data;
import org.example.data.model.Author;

@Data
public class BorrowRequest {
    private String email;
    private String title;
    private String author;
}
