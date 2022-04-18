package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.LoginControllerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("login")
public class LoginController implements LoginControllerServices {
    @Autowired
    private StudentRepository sr;
    @Autowired
    private TeacherRepository tr;

    @GetMapping("StudentLogin/{id}/{password}")
    public ResponseData studentLogin(@PathVariable("id") Long stuID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();

        try{
            Student user =sr.findById(stuID).get();
            if(user.getId().equals(stuID)&&user.getPassWord().equals(password)){
                rsp.setRspData(new Boolean(Boolean.TRUE));
            }else
                rsp.setRspData(new Boolean(Boolean.FALSE));
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

    @GetMapping("TeacherLogin/{id}/{password}")
    public ResponseData teacherLogin(@PathVariable("id") Long teacherID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();

        try{
            Teacher user =tr.findById(teacherID).get();
            if(user.getId().equals(teacherID)&&user.getPassWord().equals(password)){
                rsp.setRspData(new Boolean(Boolean.TRUE));
            }else
                rsp.setRspData(new Boolean(Boolean.FALSE));
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

    @PutMapping("StudentPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData studentModify(@PathVariable("id") Long stuID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();

        try{
            Student user =sr.findById(stuID).get();
            if(user.getId().equals(stuID)&&user.getPassWord().equals(oldpassword)){
                if (newpass1.equals(newpass2)){
                    user.setPassWord(newpass1);
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                }else
                    rsp.setRspData(new Boolean(Boolean.FALSE));
            }else
                rsp.setRspData(new Boolean(Boolean.FALSE));
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

    @PutMapping("TeacherPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData teacherModify(@PathVariable("id") Long teacherID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();

        try{
            Teacher user =tr.findById(teacherID).get();
            if(user.getId().equals(teacherID)&&user.getPassWord().equals(oldpassword)){
                if (newpass1.equals(newpass2)){
                    user.setPassWord(newpass1);
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                }else
                    rsp.setRspData(new Boolean(Boolean.FALSE));
            }else
                rsp.setRspData(new Boolean(Boolean.FALSE));
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

}
