package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface StudentRepository extends MongoRepository<Student, Long>{
    ArrayList<Student> findStudentsByLectureIdsIsContaining(Long lectureId);
}
