package com.example.zuccecho.Services.implement;

import com.example.zuccecho.DTO.TeacherDTO;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.TeacherServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.BindException;

@Service
public class TeacherServicesImplement implements TeacherServices {

    @Autowired
    private TeacherRepository tr;

    public Teacher addTeacher(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        //错误校验后用entity类存入数据库
        BeanUtils.copyProperties(teacherDTO, teacher);
        tr.save(teacher);
        return teacher;
    }

    public void deleteTeacherById(Long id){
        tr.deleteById(id);
    }

    public Teacher findTeacherById(Long id){
        return tr.findById(id).get();
    }

    public boolean updateTeacher(TeacherDTO TeacherDTO){
        try {
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(TeacherDTO,teacher);
            tr.save(teacher);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void fillTeacher(){
        Teacher a = new Teacher();
        a.setName("zwt");
        a.setId(31901031L);
        a.setPassWord("31901031");
        tr.save(a);
    }

}
