package com.upc.chatbot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static String API_BASE_URL="https://chatbotwebapiservice.azurewebsites.net/api/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitInstance(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if(retrofit==null)
        {
            gson=new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit=new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static InterfaceAPI getUserService()
    {
        InterfaceAPI userService=getRetrofitInstance().create(InterfaceAPI.class);
        return userService;
    }

}
