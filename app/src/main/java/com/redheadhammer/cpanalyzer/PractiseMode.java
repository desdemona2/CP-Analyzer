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
    Chronometer chrono;
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

        button.setOnClickListener(this::startChron);
    }
    public void startChron(View view) {
        if (isRunning){
            offset = SystemClock.elapsedRealtime();
            chronometer.stop();
            isRunning = false;
        }
        else {
            chronometer.setBase(SystemClock.elapsedRealtime()-offset);
            chronometer.start();
            isRunning = true;
        }
    }
}