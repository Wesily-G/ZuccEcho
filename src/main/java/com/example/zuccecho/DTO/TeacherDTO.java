package com.example.zuccecho.DTO;

import lombok.Data;
import java.io.Serializable;

@Data
public class TeacherDTO implements Serializable {
    private final long id;
    private final String Name;
    private final String passWord;

}