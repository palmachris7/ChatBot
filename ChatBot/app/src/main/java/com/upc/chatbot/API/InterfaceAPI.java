package com.upc.chatbot.API;

import com.upc.chatbot.models.CuestionariRequestAnswer;
import com.upc.chatbot.models.CuestionariResponseAnswer;
import com.upc.chatbot.models.NewsResponse;
import com.upc.chatbot.models.QuestionDto;
import com.upc.chatbot.models.UserLoginRequest;
import com.upc.chatbot.models.UserLoginResponse;
import com.upc.chatbot.models.UserRegisterRequest;
import com.upc.chatbot.models.UserRegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceAPI {
    @POST("User/AuthClientRegister")
    Call<UserRegisterResponse> saveUser(@Body UserRegisterRequest userRegisterRequest);

    @POST("User/AuthLogin")
    Call<UserLoginResponse> logIn(@Body UserLoginRequest userLoginRequest);

    @GET("News")
    Call<List<NewsResponse>> getNews();

    @GET("Questionary/Last")
    Call<List<QuestionDto>> getQuestionary();

    @POST("/QuestionaryAnswered")
    Call<CuestionariResponseAnswer> saveCuestionary(@Body CuestionariRequestAnswer cuestionariRequestAnswer);

}
