package com.example.zuccecho.Services.implement;

import com.example.zuccecho.DTO.StudentDTO;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Services.StudentServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@Service
public class StudentServicesImplement implements StudentServices {
    @Autowired
    private StudentRepository sr;

    @Cacheable(key = "#p0.getId()",value = "StudentID#2")
    public Student addStudent(StudentDTO studentDTO){
        Student student = new Student();
        //错误校验后用entity类存入数据库
        BeanUtils.copyProperties(studentDTO, student);
        sr.save(student);
        return student;
    }

    @CacheEvict(key = "#p0",value = "StudentID")
    public void deleteStudentById(Long id){
        sr.deleteById(id);
    }

    @Cacheable(key = "#p0",value = "StudentID#2")
    public Student findStudentById(Long id){
        return sr.findById(id).get();
    }

    @CachePut(key = "#p0.getId()",value = "StudentID#2")
    public boolean updateStudent(StudentDTO studentDTO){
        try {
            Student student = new Student();
            BeanUtils.copyProperties(studentDTO,student);
            sr.save(student);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void fillStudent(){
        Student a = new Student();
        a.setName("xh");
        a.setId(31901029L);
        a.setPassWord("31901029");
        sr.save(a);
    }
}