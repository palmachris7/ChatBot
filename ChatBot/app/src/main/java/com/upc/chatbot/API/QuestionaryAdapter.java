package com.upc.chatbot.API;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.chatbot.Actities.FragmentCuestionario;
import com.upc.chatbot.R;
import com.upc.chatbot.models.QuestionDto;

import java.util.ArrayList;
import java.util.List;

public class QuestionaryAdapter extends RecyclerView.Adapter<QuestionaryAdapter.QustionaryViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<QuestionDto> itemList=new ArrayList<>();
    private Context context;

    public QuestionaryAdapter(FragmentCuestionario fragmentCuestionario, List<QuestionDto> itemList) {

    }
    public void setData(List<QuestionDto> itemList)
    {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QustionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context=parent.getContext();
        //View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_question, viewGroup, false);
        return new QuestionaryAdapter.QustionaryViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_question,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QustionaryViewHolder holder, int i) {
        QuestionDto item = itemList.get(i);
        holder.tvItemTitle.setText(item.getQuestionText());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getAnswerDtos().size());

        // Create sub item view adapter
        AnswerAdapter subItemAdapter = new AnswerAdapter(item.getAnswerDtos());

        holder.rvSubItem.setLayoutManager(layoutManager);
        holder.rvSubItem.setAdapter(subItemAdapter);
        holder.rvSubItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class QustionaryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTitle;
        private RecyclerView rvSubItem;

        QustionaryViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);
            rvSubItem = itemView.findViewById(R.id.rv_sub_item);
        }
    }
}
