package com.example.zuccecho.Services;

import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

public interface LoginServices {
    public boolean studentLogin(Long stuID, String password);
    public boolean teacherLogin(Long teacherID,String password);
    public boolean studentModify(Long stuID,String oldpassword,String newpass1,String newpass2);
    public boolean teacherModify(Long teacherID,String oldpassword,String newpass1,String newpass2);
}
