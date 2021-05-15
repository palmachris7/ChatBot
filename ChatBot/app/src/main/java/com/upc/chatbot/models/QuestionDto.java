package com.upc.chatbot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionDto {

@SerializedName("questionId")
@Expose
private Integer questionId;
@SerializedName("questionText")
@Expose
private String questionText;
@SerializedName("answerDtos")
@Expose
private List<AnswerDto> answerDtos = null;

public QuestionDto( String questionText, List<AnswerDto> answerDtos) {
    this.questionText = questionText;
    this.answerDtos = answerDtos;
}

public Integer getQuestionId() {
return questionId;
}

public void setQuestionId(Integer questionId) {
this.questionId = questionId;
}

public String getQuestionText() {
return questionText;
}

public void setQuestionText(String questionText) {
this.questionText = questionText;
}

public List<AnswerDto> getAnswerDtos() {
return answerDtos;
}

public void setAnswerDtos(List<AnswerDto> answerDtos) {
this.answerDtos = answerDtos;
}

}