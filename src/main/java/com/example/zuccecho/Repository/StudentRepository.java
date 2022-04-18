package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long>{

}
