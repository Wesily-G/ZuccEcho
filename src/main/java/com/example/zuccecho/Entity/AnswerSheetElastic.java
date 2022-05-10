package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;


@Data
@Document(indexName = "answersheet")
public class AnswerSheetElastic {
    @Id
    @AutoId
    @Field(type = FieldType.Auto)
    private long id;
    @Field("feedbackID")
    private long feedbackID;
    @Field("studentID")
    private long studentID;
    @Field(type = FieldType.Text)
    private ArrayList<String> answers;

}