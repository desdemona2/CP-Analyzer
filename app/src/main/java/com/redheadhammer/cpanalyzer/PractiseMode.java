package com.redheadhammer.cpanalyzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class PractiseMode extends AppCompatActivity {

    private Chronometer chronometer;
    private RecyclerView recyclerView;
    private boolean isRunning = false;
    private Button button;
    private long offset = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Practise Mode");
        setContentView(R.layout.activity_practise_mode);

        chronometer = findViewById(R.id.chronometer);
        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.toggle);

        /* adding bottom bar functions */
        BottomBar bottomBar = new BottomBar(PractiseMode.this);

        button.setOnClickListener(this::startChron);
        button.setOnLongClickListener(this::onLongClick);
    }
    public void startChron(View view) {
        if (isRunning){
            offset = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isRunning = false;
            button.setText(R.string.resume);
        }
        else {
            chronometer.setBase(SystemClock.elapsedRealtime()-offset);
            chronometer.start();
            isRunning = true;
            button.setText(R.string.pause);
        }
    }

    public boolean onLongClick(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        button.setText(R.string.start);
        offset = 0;
        chronometer.stop();
        isRunning = false;
        return true;
    }
}