package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.Lecture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LectureRepository extends MongoRepository<Lecture,Long> {
}
