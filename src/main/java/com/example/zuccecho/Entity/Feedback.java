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
    @Field("id")
    private long id;
    @Field("classID")
    private long classID;
    @Field("tacherID")
    private long teacherID;
    @Field("answersheetsID")
    private ArrayList<Long> answersheetsID;
    @Field("endDate")
    private Date endDate;
    @Field("PublishModel")
    private QAModel publishModel;


}