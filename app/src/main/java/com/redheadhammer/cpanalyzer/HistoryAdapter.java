package com.redheadhammer.cpanalyzer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.CustomViewHolder> {
    private final Context context;
    private final ArrayList<String[]> info;
    public HistoryAdapter(Context context, ArrayList<String[]> info) {
        this.context = context;
        this.info = info;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.problem.setText(info.get(position)[0]);
        holder.problemState.setText(info.get(position)[1]);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView problem, problemState;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            problem = itemView.findViewById(R.id.problem);
            problemState = itemView.findViewById(R.id.problem_state);
        }
    }
}
