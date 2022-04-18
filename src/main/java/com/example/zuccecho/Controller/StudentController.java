package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Services.StudentControllerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("student")
public class StudentController implements StudentControllerServices {
    @Autowired
    private StudentRepository sr;

    @PostMapping(value="addStudent",produces = "application/json;charset=UTF-8")
    public ResponseData addStudent(@RequestBody Student student){
        ResponseData rsp = new ResponseData();
        try{
            sr.save(student);
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

    @DeleteMapping("deleteStudent/{id}")
    public ResponseData deleteStudentById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();

        try{
            sr.deleteById(id);
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

    @GetMapping("findStudent/{id}")
    public ResponseData findStudentById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();

        try{
            Student user = sr.findById(id).get();
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

    @PutMapping(value = "updateStudent",produces = "application/json;charset=UTF-8")
    public ResponseData updateStudent(@RequestBody Student user){
        ResponseData rsp = new ResponseData();

        try{
            sr.save(user);
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

    @PostMapping("fillStudent")
    public void fillStudent(){
        Student a = new Student();
        a.setName("xh");
        a.setId(31901029L);
        a.setPassWord("31901029");
        sr.save(a);
    }
}