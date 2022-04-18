package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.StudentDTO;
import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Support.ResponseData;


public interface StudentServices {
    public Student addStudent(StudentDTO studentDTO);
    public void deleteStudentById(Long id);
    public Student findStudentById(Long id);
    public boolean updateStudent(StudentDTO studentDTO);
    public void fillStudent()
}
