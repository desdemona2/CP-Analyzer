package com.redheadhammer.cpanalyzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class PractiseMode extends AppCompatActivity {

    private Chronometer chronometer;
    private RecyclerView recyclerView;
    private PractiseAdapter practiseAdapter;
    private ImageView container;
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
        container = findViewById(R.id.container);
        button = findViewById(R.id.toggle);

        /* adding bottom bar functions */
        // noinspection unused
        BottomBar bottomBar = new BottomBar(PractiseMode.this);

        /* creating practise adapter */
        practiseAdapter = new PractiseAdapter(PractiseMode.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(PractiseMode.this));
        recyclerView.setAdapter(practiseAdapter);

        button.setOnClickListener(this::startChron);
        button.setOnLongClickListener(this::onLongClick);
        container.setOnClickListener(this::onClickChron);
    }
    public void onClickChron(View view){

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