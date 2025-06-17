package org.example.data.repository;

import org.example.data.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Libraries extends MongoRepository<Library,String> {
}
