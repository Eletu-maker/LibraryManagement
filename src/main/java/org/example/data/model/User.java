package org.example.data.model;

import lombok.Data;

@Data
public abstract class User {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean login ;



}
