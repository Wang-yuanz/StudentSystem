package com.alvis.exam.domain.exam;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaperTitleItemObject {

    private String name;
    private int totalScore;
    private List<ExamPaperQuestionItemObject> questionItems;
}
