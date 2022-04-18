package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "Lecture")
public class Lecture {
    @Id
    @AutoId
    @Field("id")
    private long id;
    @NotBlank(message = "Name can not be empty.")
    @Field("Name")
    private String Name;
    @NotBlank(message = "Area can not be empty.")
    @Field("area")
    private String area;
    @NotBlank(message = "Time can not be empty.")
    @Field("time")
    private Date time;
    @NotBlank(message = "Teacher can not be empty.")
    @Field("TeacherID")
    private long teacherID;
    @Field("isOpen")
    private boolean isOpen;

}