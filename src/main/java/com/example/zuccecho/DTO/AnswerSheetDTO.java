package com.example.zuccecho.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;


@Data
public class AnswerSheetDTO implements Serializable {
    private final long id;
    private final long studentID;
    private final long feedbackID;
    private final ArrayList<String> answers;
}