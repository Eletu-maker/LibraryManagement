package org.example.data.repository;

import org.example.data.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Authors extends MongoRepository<Author,String> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByName(String name);
    Author findByName(String name);
    Author findByEmail(String email);
    boolean existsByEmail(String email);

   // List<Author> findAllByExist(boolean exist);
}
