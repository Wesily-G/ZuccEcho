package com.example.zuccecho.Services;

import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Support.ResponseData;


public interface StudentControllerServices {
    public ResponseData addStudent(Student student);
    public ResponseData deleteStudentById(Long id);
    public ResponseData findStudentById(Long id);
    public ResponseData updateStudent(Student user);

}
