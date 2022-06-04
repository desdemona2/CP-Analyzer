package com.redheadhammer.cpanalyzer;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class CompetitiveMode extends AppCompatActivity {

    boolean timerRunning = false;
    private CustomTimer customTimer;
    private CustomUpTimer customUpTimer;
    private Button toggle;
    protected static int question = 0;
    private RecyclerAdapter recyclerAdapter;
    private String[] questionStatus;
    private ArrayList<String[]> info;
    private final String[] data = new String[2];
    private final int MAX_SIZE = 15;
    private int size;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Competition Mode");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.purple_700, this.getTheme())));

        setContentView(R.layout.activity_main);

        /* getting variables from previous activity */
        Intent intent = getIntent();
        time = intent.getIntExtra("totalTime", 10_800_000);
        size = intent.getIntExtra("questions", 7);

        /* attach views with its corresponding ui-elements */
        TextView totalTime = findViewById(R.id.total_time);
        TextView questionTime = findViewById(R.id.question_time);
        toggle = findViewById(R.id.button);

        info = FileHelper.readData(CompetitiveMode.this);
        /* setting recyclerView and its elements */
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* adding bottom bar functions */
        @SuppressWarnings("unused") BottomBar bottomBar = new BottomBar(CompetitiveMode.this);

        questionStatus = new String[size];
        Arrays.fill(questionStatus, "Pending");

        recyclerAdapter = new RecyclerAdapter(this, questionStatus);
        recyclerView.setAdapter(recyclerAdapter);

        /* setting up both timers */
        customUpTimer = new CustomUpTimer(questionTime);
        customTimer = new CustomTimer(totalTime, time, info, customUpTimer,size, MAX_SIZE, toggle);

        /* set total-time so that it doesn't show default value */
        customTimer.updateTimer();

        toggle.setOnClickListener(this::onClick);
        toggle.setOnLongClickListener(this::onLongClick);
        questionTime.setOnClickListener(this::onSolved);
    }

    public void onSolved(View view) {
        if (question < size && timerRunning) {
            String singleTime = customUpTimer.updateTimer();
            questionStatus[question] = singleTime;
            customUpTimer.resetTimer();
            customUpTimer.startTimer();
            recyclerAdapter.notifyItemChanged(question++);

            if (question < size) {
                questionStatus[question] = "Solving";
                recyclerAdapter.notifyItemChanged(question);
            } else {
                /* data[0] = LocalDate.now().toString(); */
                data[0] = new SimpleDateFormat("MM/dd HH:mm",
                        Locale.US).format(Calendar.getInstance().getTime());

                data[1] = question + "/" + size + " Solved " + customTimer.timeFormat(time);;
                if (info.size() > MAX_SIZE) {
                    info.remove(0);
                }
                info.add(data);

                FileHelper.writeData( info,CompetitiveMode.this);
                customTimer.stopTimer();
                customUpTimer.stopTimer();
                timerRunning = false;
            }
        }
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
            questionStatus[0] = "Solving";
            recyclerAdapter.notifyItemChanged(0);

            timerRunning = true;
            toggle.setText(R.string.pause);
        }
    }

    public boolean onLongClick(View view) {
        Arrays.fill(questionStatus, "Pending");
        timerRunning = false;
        customTimer.resetTimer();
        customUpTimer.resetTimer();

        recyclerAdapter.notifyItemRangeChanged(0, question + 1);
        Log.d("RESET", question + "");
        question = 0;

        toggle.setText(R.string.start);
        return true;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
