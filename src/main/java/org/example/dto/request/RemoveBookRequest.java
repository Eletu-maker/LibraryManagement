package org.example.dto.request;

import lombok.Data;

@Data
public class RemoveBookRequest {
    private String title;
    private String author;
}
