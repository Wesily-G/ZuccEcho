package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.QAModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QAModelRepository extends MongoRepository<QAModel, Long> {

}
