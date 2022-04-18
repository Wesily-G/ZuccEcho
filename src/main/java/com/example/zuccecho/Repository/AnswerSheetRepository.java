package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.AnswerSheet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface AnswerSheetRepository extends MongoRepository<AnswerSheet,Long> {
    ArrayList<AnswerSheet> findAnswerSheetByFeedbackID(long feedbackID);
    ArrayList<AnswerSheet> findAllByAnswersIsNull(long feedbackID);
}
