package com.example.zuccecho.Entity;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document(collection = "QAModel")
public class QAModel implements  Serializable{
    @Id
    @AutoId
    @Field("id")
    private Long id;
    @NotBlank(message = "Name can not be empty.")
    @Field("name")
    private String name;
    @Field("questions")
    private ArrayList<String> questions;
    @Field("selections")
    private ArrayList<String> selections;
    @Field("questionsType")//0单选，1多选，2填空
    private ArrayList<Integer> questionsType;

}
