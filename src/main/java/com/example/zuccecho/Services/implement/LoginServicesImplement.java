package com.example.zuccecho.Services.implement;

import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.LoginServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Service
public class LoginServicesImplement implements LoginServices {
    @Autowired
    private StudentRepository sr;
    @Autowired
    private TeacherRepository tr;

    public boolean studentLogin(Long stuID,String password){
        Student user =sr.findById(stuID).get();
        if(user.getId().equals(stuID)&&user.getPassWord().equals(password))
            return true;
        else
            return false;
    }

    public boolean teacherLogin(Long teacherID,String password){
        Teacher user =tr.findById(teacherID).get();
        if(user.getId().equals(teacherID)&&user.getPassWord().equals(password))
            return true;
        else
            return false;
    }

    public boolean studentModify(Long stuID,String oldpassword,String newpass1,String newpass2){
        Student user =sr.findById(stuID).get();
        if(user.getId().equals(stuID)&&user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }

    public boolean teacherModify(Long teacherID,String oldpassword,String newpass1,String newpass2){
        Teacher user = tr.findById(teacherID).get();
        if(user.getId().equals(teacherID)&&user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }

}
