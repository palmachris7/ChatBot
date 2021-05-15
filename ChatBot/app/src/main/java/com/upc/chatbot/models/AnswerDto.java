package com.upc.chatbot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerDto {

    @SerializedName("answerId")
    @Expose
    private Integer answerId;
    @SerializedName("answerText")
    @Expose
    private String answerText;
    @SerializedName("isCorrect")
    @Expose
    private Boolean isCorrect;

    public AnswerDto(Integer answerId, String answerText) {
        this.answerId = answerId;
        this.answerText = answerText;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
