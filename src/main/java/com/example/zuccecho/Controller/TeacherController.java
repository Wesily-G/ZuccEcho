package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.TeacherControllerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("teacher")
public class TeacherController implements TeacherControllerServices {

    @Autowired
    private TeacherRepository tr;

    @PostMapping(value="addTeacher",produces = "application/json;charset=UTF-8")
    public ResponseData addTeacher(@RequestBody Teacher teacher){
        ResponseData rsp = new ResponseData();
        try{
            tr.save(teacher);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }

        }

        return rsp;
    }

    @DeleteMapping("deleteTeacher/{id}")
    public ResponseData deleteTeacherById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();

        try{
            tr.deleteById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
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

    @GetMapping("findTeacher/{id}")
    public ResponseData findTeacherById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();

        try{
            Teacher user = tr.findById(id).get();
            rsp.setRspData(user);
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

    @PutMapping(value = "updateTeacher",produces = "application/json;charset=UTF-8")
    public ResponseData updateTeacher(@RequestBody Teacher user){
        ResponseData rsp = new ResponseData();

        try{
            tr.save(user);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

    @GetMapping("fillTeacher")
    public void fillTeacher(){
        Teacher a = new Teacher();
        a.setName("zwt");
        a.setId(31901031L);
        a.setPassWord("31901031");
        tr.save(a);
    }

}
