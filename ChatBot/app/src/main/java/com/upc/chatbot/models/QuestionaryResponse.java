package com.upc.chatbot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionaryResponse {

    @SerializedName("questionaryId")
    @Expose
    private Integer questionaryId;
    @SerializedName("questionaryName")
    @Expose
    private String questionaryName;
    @SerializedName("questionDto")
    @Expose
    private List<QuestionDto> questionDto = null;

    public Integer getQuestionaryId() {
        return questionaryId;
    }

    public void setQuestionaryId(Integer questionaryId) {
        this.questionaryId = questionaryId;
    }

    public String getQuestionaryName() {
        return questionaryName;
    }

    public void setQuestionaryName(String questionaryName) {
        this.questionaryName = questionaryName;
    }

    public List<QuestionDto> getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(List<QuestionDto> questionDto) {
        this.questionDto = questionDto;
    }

}






