package com.example.zuccecho.Services;

import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

public interface LoginServices {
    public boolean studentLogin(long stuID, String password);
    public boolean teacherLogin(long teacherID,String password);
    public boolean studentModify(long stuID,String oldpassword,String newpass1,String newpass2);
    public boolean teacherModify(long teacherID,String oldpassword,String newpass1,String newpass2);
}
