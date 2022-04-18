package com.example.zuccecho.Services;

import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

public interface LoginControllerServices {
    public ResponseData studentLogin(Long stuID, String password);
    public ResponseData teacherLogin(Long teacherID,String password);
    public ResponseData studentModify(Long stuID,String oldpassword,String newpass1,String newpass2);
    public ResponseData teacherModify(Long teacherID,String oldpassword,String newpass1,String newpass2);
}
