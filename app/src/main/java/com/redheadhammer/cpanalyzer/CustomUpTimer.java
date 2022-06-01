package com.redheadhammer.cpanalyzer;


import android.os.CountDownTimer;
import android.widget.TextView;

public class CustomUpTimer {
    private final TextView questionTimer;
    private CountDownTimer countUpTimer;
    private final long DEFAULT = 10_800_000;
    private long upTime = DEFAULT-1000;
    private final long INTERVAL = 1000;

    public CustomUpTimer(TextView text) {
        this.questionTimer = text;
    }

    public void startTimer(){
        countUpTimer = new CountDownTimer(upTime, INTERVAL) {
            @Override
            public void onTick(long l) {
                upTime = l;
                updateTimer();
            }

            @Override
            public void onFinish() {}
        }.start();
    }

    public void stopTimer(){
        countUpTimer.cancel();
        updateTimer();
    }

    public void resetTimer(){
        countUpTimer.cancel();
        upTime = DEFAULT;
        updateTimer();
    }

    public void updateTimer(){
        int value = (int)DEFAULT - (int)upTime;

        StringBuilder timeFormat = new StringBuilder();

        int hours = value / (1000*60*60);
        if (hours < 10) {
            timeFormat.append("0").append(hours);
        } else {
            timeFormat.append(hours);
        }

        int minutes = value / (1000*60) % 60;
        if (minutes < 10) {
            timeFormat.append(":").append("0").append(minutes);
        } else {
            timeFormat.append(":").append(minutes);
        }

        int seconds = value / (1000) % 60;
        if (seconds < 10) {
            timeFormat.append(":").append("0").append(seconds);
        } else {
            timeFormat.append(":").append(seconds);
        }

        questionTimer.setText(timeFormat.toString());
    }
}
