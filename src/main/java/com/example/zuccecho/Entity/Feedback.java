package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@Document(collection = "Feedback")
public class Feedback {
    @Id
    @AutoId
    @Field("classID")
    private Long classID;
    @Field("TeacherID")
    private Long TeacherID;
    @Field("answersheetsID")
    private ArrayList<Long> answersheetsID;
    @Field("EndDate")
    private Date endDate;

}