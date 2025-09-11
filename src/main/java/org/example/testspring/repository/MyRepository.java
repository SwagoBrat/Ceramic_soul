package org.example.testspring.repository;

import org.example.testspring.model.DataForm;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyRepository extends MongoRepository<DataForm, String> {

}
