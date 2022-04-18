package com.example.zuccecho.Services;

import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

public interface TeacherControllerServices {
    public ResponseData addTeacher(Teacher teacher);
    public ResponseData deleteTeacherById(Long id);
    public ResponseData findTeacherById(Long id);
    public ResponseData updateTeacher(Teacher user);
}
