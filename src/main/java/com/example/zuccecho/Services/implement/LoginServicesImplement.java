package com.example.zuccecho.Services.implement;

import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "LoginService")
public class LoginServicesImplement implements LoginServices {
    @Autowired
    private StudentRepository sr;
    @Autowired
    private TeacherRepository tr;

    @Cacheable(key = "#p0+'-'+#p1",value = "stuLogin#2")
    public boolean studentLogin(long stuID,String password){
        Student user =sr.findById(stuID).get();
        if(user.getId()==stuID && user.getPassWord().equals(password))
            return true;
        else
            return false;
    }

    @Cacheable(key = "#p0+'-'+#p1",value = "teacherLogin#6")
    public boolean teacherLogin(long teacherID,String password){
        Teacher user =tr.findById(teacherID).get();
        if(user.getId()==teacherID && user.getPassWord().equals(password))
            return true;
        else
            return false;
    }

    @CacheEvict(key = "#p0+'-'+#p1")
    @Cacheable(key = "#p0+'-'+#p2",value = "stuLogin#2")
    public boolean studentModify(long stuID,String oldpassword,String newpass1,String newpass2){
        Student user =sr.findById(stuID).get();
        if(user.getId()==stuID && user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }

    @CacheEvict(key = "#p0+'-'+#p1")
    @Cacheable(key = "#p0+'-'+#p2",value = "teacherLogin#6")
    public boolean teacherModify(long teacherID,String oldpassword,String newpass1,String newpass2){
        Teacher user = tr.findById(teacherID).get();
        if(user.getId()==teacherID && user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }

}
