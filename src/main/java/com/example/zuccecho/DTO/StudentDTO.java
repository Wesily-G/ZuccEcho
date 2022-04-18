package com.example.zuccecho.DTO;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class StudentDTO implements Serializable {
    private final Long id;
    private final String Name;
    private final String passWord;
}