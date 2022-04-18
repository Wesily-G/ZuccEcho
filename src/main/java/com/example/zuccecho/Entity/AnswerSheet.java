package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@Document(collection = "AnswerSheet")
public class AnswerSheet{
    @Id
    @AutoId
    @Field("id")
    private Long id;
    @Field("studentID")
    private Long studentID;
    @Field("answers")
    private ArrayList<String> answers;

}