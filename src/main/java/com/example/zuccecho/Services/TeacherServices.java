package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.TeacherDTO;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

public interface TeacherServices {
    public Teacher addTeacher(TeacherDTO teacherDTO);
    public void deleteTeacherById(Long id);
    public Teacher findTeacherById(Long id);
    public boolean updateTeacher(TeacherDTO teacherDTO);
    public void fillTeacher();
}
