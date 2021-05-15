package com.upc.chatbot.Actities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.upc.chatbot.API.InterfaceAPI;
import com.upc.chatbot.API.NewsAdapter;
import com.upc.chatbot.API.QuestionaryAdapter;
import com.upc.chatbot.API.RetrofitClientInstance;
import com.upc.chatbot.R;
import com.upc.chatbot.models.AnswerDto;
import com.upc.chatbot.models.CuestionariRequestAnswer;
import com.upc.chatbot.models.CuestionariResponseAnswer;
import com.upc.chatbot.models.NewsResponse;
import com.upc.chatbot.models.QuestionDto;
import com.upc.chatbot.models.QuestionaryResponse;
import com.upc.chatbot.models.UserRegisterResponse;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCuestionario extends Fragment {

    List<QuestionDto> questionaryList;
    private Context context;
    RecyclerView rvItem;
    QuestionaryAdapter questionaryAdapter;
    private Button btnRegistroCuestionario;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=container.getContext();
        View view = null;
        view  = inflater.inflate(R.layout.fragment_cuestionario, container,false);

        rvItem=(RecyclerView) view.findViewById(R.id.rv_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        QuestionaryAdapter myAdapter = new QuestionaryAdapter(this, questionaryList);
        rvItem.setLayoutManager(new GridLayoutManager(context,5));
        rvItem.setAdapter(myAdapter);


        rvItem.setLayoutManager(layoutManager);
        rvItem.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL));

        questionaryAdapter=new QuestionaryAdapter(this, questionaryList);

        getAllQuestions();

        btnRegistroCuestionario=(Button)view.findViewById(R.id.btn_RegisterCuestionary);

        btnRegistroCuestionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAllQuestions();
            }
        });

        return view;
    }

    public void getAllQuestions() {
        Call<List<QuestionDto>> questionaryList = RetrofitClientInstance.getUserService().getQuestionary();

        questionaryList.enqueue(new Callback<List<QuestionDto>>() {
            @Override
            public void onResponse(Call<List<QuestionDto>> call, Response<List<QuestionDto>> response) {
                if(response.isSuccessful()) {

                    List<QuestionDto> questonaryResponses = response.body();

                    questionaryAdapter.setData(questonaryResponses);

                    rvItem.setAdapter(questionaryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<QuestionDto>> call, Throwable t) {
                Log.e("Success",t.getLocalizedMessage());
            }
        });


    }

    public void saveAllQuestions()
    {
        CuestionariRequestAnswer cuestionariRequestAnswer=new CuestionariRequestAnswer();
        Call<CuestionariResponseAnswer> cuestionariResponseAnswerCall= RetrofitClientInstance.getUserService().saveCuestionary(cuestionariRequestAnswer);

        cuestionariResponseAnswerCall.enqueue(new Callback<CuestionariResponseAnswer>() {
            @Override
            public void onResponse(Call<CuestionariResponseAnswer> call, Response<CuestionariResponseAnswer> response) {

                if(response.isSuccessful())
                {
                    CuestionariResponseAnswer responseJson=response.body();
                    Toast.makeText(getContext(),"Registro correcto: "+responseJson,Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getContext(),"Ha fallado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CuestionariResponseAnswer> call, Throwable t) {
                Toast.makeText(getContext(),"Ha fallado"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }



   /* private List<QuestionDto> buildItemList() {
        questionaryList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            QuestionDto item = new QuestionDto("item"+i, buildSubItemList());
            questionaryList.add(item);
        }
        return questionaryList;
    }

    private List<AnswerDto> buildSubItemList() {
        List<AnswerDto> subItemList = new ArrayList<>();
        for (int i=0; i<4; i++) {
            AnswerDto subItem = new AnswerDto(i, "sub item "+i);
            subItemList.add(subItem);
        }
        return subItemList;
    }*/


}
