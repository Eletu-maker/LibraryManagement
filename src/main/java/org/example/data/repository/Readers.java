package org.example.data.repository;

import org.example.data.model.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Readers extends MongoRepository<Reader,String> {
    boolean existsByPhoneNumber(String phoneNumber);

    Reader findByEmail(String email);

    boolean existsByEmail(String email);
}
