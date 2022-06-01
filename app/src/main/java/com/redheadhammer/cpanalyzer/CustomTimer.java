package com.redheadhammer.cpanalyzer;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CustomTimer {
    /* add interval of 1 sec. */
    private final long INTERVAL = 1000;
    private long timeLeft;
    private long TIME;

    CountDownTimer countDownTimer;
    TextView text;

    public CustomTimer(TextView text, long timeLeft) {
        Log.i("CustomTimer", "CustomTimer class called");
        this.text = text;
        this.timeLeft = timeLeft;
        this.TIME = timeLeft;
    }

    public void startTimer() {
        Log.i("CustomTimer", "New timer is being created");
        countDownTimer = new CountDownTimer(timeLeft, INTERVAL) {
            @Override
            public void onTick(long l) {
                Log.i("CustomTimer", "updating interval");
                timeLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Log.i("CustomTimer", "Timer has finished");
                Toast.makeText(text.getContext(), R.string.timer_finished,
                        Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void stopTimer() {
        Log.i("CustomTimer", "Timer Stopped");
        countDownTimer.cancel();
    }

    public void resetTimer(){
        countDownTimer.cancel();
        timeLeft = TIME;
        updateTimer();
        Log.i("CustomTimer", "Timer is getting a reset");
        Toast.makeText(text.getContext(), R.string.reset_timer, Toast.LENGTH_SHORT).show();
    }

    public void updateTimer() {
        StringBuilder timeFormat = new StringBuilder();

        int hours = (int)timeLeft / (1000*60*60);
        if (hours < 10) {
            timeFormat.append("0").append(hours);
        } else {
            timeFormat.append(hours);
        }

        int minutes = (int)timeLeft / (1000*60) % 60;
        if (minutes < 10) {
            timeFormat.append(":").append("0").append(minutes);
        } else {
            timeFormat.append(":").append(minutes);
        }

        int seconds = (int) timeLeft / (1000) % 60;
        if (seconds < 10) {
            timeFormat.append(":").append("0").append(seconds);
        } else {
            timeFormat.append(":").append(seconds);
        }

        text.setText(timeFormat.toString());
        Log.i("CustomTimer", "Timer view is updating");
    }
}
