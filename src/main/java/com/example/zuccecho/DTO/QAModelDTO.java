package com.example.zuccecho.DTO;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Data
public class QAModelDTO implements  Serializable{
    private final long id;
    private final String name;
    private final ArrayList<String> questions;
    private final ArrayList<String> selections;
    private final ArrayList<Integer> questionsType;
}
