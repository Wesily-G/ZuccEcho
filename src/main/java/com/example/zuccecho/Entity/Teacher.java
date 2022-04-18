package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Document(collection = "Teacher")
public class Teacher {
    @Id
    @AutoId
    @Field("id")
    private Long id;
    @NotBlank(message = "Name can not be empty.")
    @Field("Name")
    private String Name;
    @Length(min = 8,max = 20,message = "password length from 8 to 20")
    @Field("passWord")
    private String passWord;

}