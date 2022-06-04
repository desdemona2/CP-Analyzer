package com.redheadhammer.cpanalyzer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class BottomBar {
    private final Context context;
    private ImageView home, single, history;

    public BottomBar(Context context) {
        this.context = context;
        Log.d("SETUP", "Creating Constructor");
        attach();
    }

    private void attach() {
        home = ((Activity) context).findViewById(R.id.home);
        single = ((Activity) context).findViewById(R.id.single);
        history = ((Activity) context).findViewById(R.id.history);

        Log.d("SETUP", "setting Views");
        this.listeners();
    }

    private void listeners(){
        home.setOnClickListener(this::onClickHome);
        single.setOnClickListener(this::onClickPractise);
        history.setOnClickListener(this::onClickHistory);
        Log.d("SETUP", "Listeners added");
    }

    public void onClickHome (View view){
        Intent intent = new Intent(context, CompetitiveMode.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.context.startActivity(intent);
        Log.d("SETUP", "Changing Activity to Home");
    }

    public void onClickPractise(View view) {
        Intent intent = new Intent(context, PractiseMode.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.context.startActivity(intent);
        Log.d("SETUP", "Changing Activity to Practise Mode");
    }

    public void onClickHistory(View view) {
        Intent intent = new Intent(context, History.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.context.startActivity(intent);
    }
}
