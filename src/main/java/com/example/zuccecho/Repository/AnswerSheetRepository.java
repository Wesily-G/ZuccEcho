package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.AnswerSheet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerSheetRepository extends MongoRepository<AnswerSheet,Long> {

}
