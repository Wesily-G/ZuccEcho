package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;

@Data
@Document(indexName = "model")
public class QAModelElastic {
    @Id
    @AutoId
    @Field(type = FieldType.Auto)
    private long id;
    @NotBlank(message = "Name can not be empty.")
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private ArrayList<String> questions;
    @Field("selections")
    private ArrayList<String> selections;
    @Field("questionsType")//0单选，1多选，2填空
    private ArrayList<Integer> questionsType;
}
