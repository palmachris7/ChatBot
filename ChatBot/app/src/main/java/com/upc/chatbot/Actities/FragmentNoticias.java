package com.upc.chatbot.Actities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.upc.chatbot.API.NewsAdapter;
import com.upc.chatbot.API.RetrofitClientInstance;
import com.upc.chatbot.R;
import com.upc.chatbot.models.NewsResponse;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNoticias extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private Context context;
    NewsAdapter newsAdapter;
    List<NewsResponse> listNews;
    private ExpandableTextView textViewExpand;
    private TextView textViewDescription;
    private String description;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context=container.getContext();
        View view = null;

        view  = inflater.inflate(R.layout.fragment_noticias, container,false);

        toolbar=(Toolbar) view.findViewById(R.id.toolbar);

        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerViewNews);
        NewsAdapter myAdapter = new NewsAdapter(this,listNews);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(myAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL));

        newsAdapter=new NewsAdapter(this, listNews);

        getAllNews();

        /*textViewExpand=(ExpandableTextView)view.findViewById(R.id.expand_text_view);
        textViewDescription=(TextView)view.findViewById(R.id.txtBodyNews);

        description=textViewDescription.getText().toString();

        textViewExpand.setText(description);*/


        return view;

    }

    public void getAllNews()
    {
        Call<List<NewsResponse>> newsList= RetrofitClientInstance.getUserService().getNews();

        newsList.enqueue(new Callback<List<NewsResponse>>() {
            @Override
            public void onResponse(Call<List<NewsResponse>> call, Response<List<NewsResponse>> response) {
                if(response.isSuccessful())
                {
                    List<NewsResponse> newsResponses=response.body();

                    newsAdapter.setData(newsResponses);

                    recyclerView.setAdapter(newsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<NewsResponse>> call, Throwable t) {
                Log.e("Success",t.getLocalizedMessage());
            }
        });

    }
}
