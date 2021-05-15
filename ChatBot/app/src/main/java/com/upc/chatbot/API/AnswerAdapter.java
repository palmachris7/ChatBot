package com.upc.chatbot.API;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.chatbot.R;
import com.upc.chatbot.models.AnswerDto;

import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.SubItemViewHolder> {

    private List<AnswerDto> subItemList=new ArrayList<>();

    AnswerAdapter(List<AnswerDto> subItemList) {
        this.subItemList = subItemList;
    }

    @NonNull
    @Override
    public AnswerAdapter.SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_answer, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.SubItemViewHolder holder, int i) {
        AnswerDto subItem = subItemList.get(i);
        holder.tvSubItemTitle.setText(subItem.getAnswerText());
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    public class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;

        SubItemViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
        }
    }
}
