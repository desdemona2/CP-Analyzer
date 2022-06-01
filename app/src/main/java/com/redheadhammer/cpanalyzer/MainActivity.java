package com.redheadhammer.cpanalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean timerRunning = false;
    private CustomTimer customTimer;
    private CustomUpTimer customUpTimer;
    private Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView totalTime = findViewById(R.id.total_time);
        TextView questionTime = findViewById(R.id.question_time);
        toggle = findViewById(R.id.button);

        customTimer = new CustomTimer(totalTime);
        customUpTimer = new CustomUpTimer(questionTime);

        toggle.setOnClickListener(this::onClick);
        toggle.setOnLongClickListener(this::onLongClick);
    }

    public void onClick(View view) {
        if (timerRunning) {
            customTimer.stopTimer();
            customUpTimer.stopTimer();

            timerRunning = false;
            toggle.setText(R.string.resume);
        } else {
            customTimer.startTimer();
            customUpTimer.startTimer();

            timerRunning = true;
            toggle.setText(R.string.pause);
        }
    }

    public boolean onLongClick(View view) {
        timerRunning = false;
        customTimer.resetTimer();
        customUpTimer.resetTimer();

        toggle.setText(R.string.start);
        return true;
    }
}