package com.upc.chatbot.models;

import java.util.List;

public class CuestionariRequestAnswer{
    public int userId;
    public List<AnswerCuestionary> questionAnsweredDtos;

    public CuestionariRequestAnswer(int userId, List<AnswerCuestionary> questionAnsweredDtos) {
        this.userId = userId;
        this.questionAnsweredDtos = questionAnsweredDtos;
    }

    public CuestionariRequestAnswer() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AnswerCuestionary> getQuestionAnsweredDtos() {
        return questionAnsweredDtos;
    }

    public void setQuestionAnsweredDtos(List<AnswerCuestionary> questionAnsweredDtos) {
        this.questionAnsweredDtos = questionAnsweredDtos;
    }
}