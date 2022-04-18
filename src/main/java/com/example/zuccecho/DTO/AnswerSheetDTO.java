package com.example.zuccecho.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;


@Data
public class AnswerSheetDTO implements Serializable {
    private final Long id;
    private final Long studentID;
    private final ArrayList<String> answers;
}