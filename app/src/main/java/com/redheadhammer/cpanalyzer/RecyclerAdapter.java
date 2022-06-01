package com.redheadhammer.cpanalyzer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {

    private final String[] questionStatus;
    private final Context context;

    public RecyclerAdapter(Context context, String[] questionStatus) {
        this.context = context;
        this.questionStatus = questionStatus;
        Arrays.fill(questionStatus, "Pending");
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.question.setText(String.format(Locale.ENGLISH,
                "%s %d", "Problem", position+1));
        holder.status.setText(questionStatus[position]);
        if (questionStatus[position].equals("Solving")) {
            holder.question.setTextColor(ContextCompat.getColor(context,
                    android.R.color.holo_blue_bright));
            holder.status.setTextColor(ContextCompat.getColor(context,
                    android.R.color.holo_blue_bright));
        } else if (!(questionStatus[position].equals("Solving")||
                questionStatus[position].equals("Pending"))){
            holder.question.setTextColor(ContextCompat.getColor(context,
                    R.color.green));
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return questionStatus.length;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        TextView status;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.problem);
            status = itemView.findViewById(R.id.problem_state);
        }
    }
}
