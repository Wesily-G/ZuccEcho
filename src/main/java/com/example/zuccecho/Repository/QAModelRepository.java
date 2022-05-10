package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.QAModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface QAModelRepository extends MongoRepository<QAModel, Long> {
    ArrayList<QAModel> findAllById(long modelID);
}


