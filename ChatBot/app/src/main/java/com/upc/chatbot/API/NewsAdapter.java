package com.upc.chatbot.API;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.chatbot.Actities.FragmentNoticias;
import com.upc.chatbot.R;
import com.upc.chatbot.models.NewsResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterVH> {

    private List<NewsResponse> newsResponsesList=new ArrayList<>();
    private Context context;

    public NewsAdapter(FragmentNoticias fragmentNoticias, List<NewsResponse> listNews) {

    }
    public void setData(List<NewsResponse> newsResponsesList) {
        this.newsResponsesList=newsResponsesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new NewsAdapter.NewsAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_news,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterVH holder, int position) {
        NewsResponse newsResponse=newsResponsesList.get(position);
        String title=newsResponse.getTitle();
        String body=newsResponse.getBody();
        String Subtitle=newsResponse.getSubtitle();

        holder.title.setText(title);
        holder.body.setText(body);
        holder.Subtitle.setText(Subtitle);

        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.expand.getText().toString().equals("Expandir Noticia")) {
                    holder.body.setMaxLines(100);
                    holder.expand.setText("Contraer texto");
                }else
                {
                    holder.body.setMaxLines(3);
                    holder.expand.setText("Expandir Noticia");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsResponsesList.size();
    }

    public class NewsAdapterVH extends RecyclerView.ViewHolder {
        TextView title;
        TextView Subtitle;
        TextView body;
        TextView expand;

        public NewsAdapterVH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.txtTitleNews);
            body=itemView.findViewById(R.id.txtBodyNews);
            Subtitle=itemView.findViewById(R.id.txtSubTitleNews);
            expand=itemView.findViewById(R.id.expandText);

        }
    }
}
