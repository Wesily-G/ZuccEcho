package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher,Long> {
}
