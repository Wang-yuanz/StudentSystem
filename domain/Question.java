package com.alvis.exam.domain;

import com.alvis.exam.domain.enums.QuestionTypeEnum;
import com.alvis.exam.utility.ExamUtil;

import java.util.Date;
import java.util.List;

public class Question {
    private Integer id;

    private Integer questionType;

    private List<CapterQuestion> capterQuestions;

    private Integer score;

    private Integer difficult;

    private Integer infoTextContentId;

    private Integer createUser;

    private Integer status;

    private Date createTime;

    private Boolean deleted;

    private String correct;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public Integer getInfoTextContentId() {
        return infoTextContentId;
    }

    public void setInfoTextContentId(Integer infoTextContentId) {
        this.infoTextContentId = infoTextContentId;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct == null ? null : correct.trim();
    }

    public List<CapterQuestion> getCapterQuestions() {
        return capterQuestions;
    }

    public void setCapterQuestions(List<CapterQuestion> capterQuestions) {
        this.capterQuestions = capterQuestions;
    }

    public void setCorrectFromVM(String correct, List<String> correctArray) {
        int qType = this.getQuestionType();
        if (qType == QuestionTypeEnum.MultipleChoice.getCode()) {
            String correctJoin = ExamUtil.contentToString(correctArray);
            this.setCorrect(correctJoin);
        } else {
            this.setCorrect(correct);
        }
    }

}