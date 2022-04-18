package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback,Long>{
}
