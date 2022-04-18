package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.LoginServices;
import com.example.zuccecho.Services.StudentServices;
import com.example.zuccecho.Services.TeacherServices;
import com.example.zuccecho.Services.implement.LoginServicesImplement;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private StudentServices studentServices;
    @Autowired
    private TeacherServices teacherServices;
    @Autowired
    private LoginServices loginServices;

    @GetMapping("StudentLogin/{id}/{password}")
    public ResponseData studentLogin(@PathVariable("id") Long stuID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.studentLogin(stuID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @GetMapping("TeacherLogin/{id}/{password}")
    public ResponseData teacherLogin(@PathVariable("id") Long teacherID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.teacherLogin(teacherID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @PutMapping("StudentPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData studentModify(@PathVariable("id") Long stuID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.studentModify(stuID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @PutMapping("TeacherPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData teacherModify(@PathVariable("id") Long teacherID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.teacherModify(teacherID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

}
