package com.redheadhammer.cpanalyzer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PractiseAdapter extends RecyclerView.Adapter<PractiseAdapter.CustomViewHolder> {
    String[] state = {"Read Prob", "Bruteforce",
            "Constraints", "Analyze I/O", "Write Code",
            "Run on Samples", "Correct if wrong", "Submit"};
    private final Context context;
    private final String[] status;

    public PractiseAdapter(Context context, String[] status) {
        this.context = context;
        this.status = status;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent ,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.problem.setText(state[position]);
        if (!status[position].equals("N/A")) {
            holder.problem.setTextColor(ContextCompat.getColor(context,
                    R.color.green));
            holder.problemState.setTextColor(ContextCompat.getColor(context,
                    R.color.green));
            holder.problemState.setText(humanReadable(status[position]));
        } else {
            holder.problemState.setText(status[position]);
        }
    }

    @Override
    public int getItemCount() {
        return state.length;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView problem, problemState;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            problem = itemView.findViewById(R.id.problem);
            problemState = itemView.findViewById(R.id.problem_state);
        }
    }
    private String humanReadable(String value) {
        StringBuilder time = new StringBuilder();
        int seconds = Integer.parseInt(value) / 1000;
        int minutes = seconds/60;
        if (minutes < 10) {
            time.append("0");
        }
        time.append(minutes).append(":");


        seconds %= 60;
        if (seconds < 10) {
            time.append("0");
        }
        time.append(seconds);

        return time.toString();
    }
}
