package com.redheadhammer.cpanalyzer;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CustomTimer {
    /* add interval of 1 sec. */
    private final long INTERVAL = 1000;
    protected long timeLeft;
    private final long TIME;
    private final int size;
    private final int MAX_SIZE;
    private final Button toggle;

    CountDownTimer countDownTimer;
    TextView text;
    ArrayList<String[]> info;
    CustomUpTimer customUpTimer;

    public CustomTimer(TextView text, long timeLeft, ArrayList<String[]> info,
                       CustomUpTimer customUpTimer, int size, int MAX_SIZE, Button toggle) {
        Log.i("CustomTimer", "CustomTimer class called");
        this.text = text;
        this.timeLeft = timeLeft;
        this.TIME = timeLeft;
        this.info = info;
        this.customUpTimer = customUpTimer;
        this.size = size;
        this.MAX_SIZE = MAX_SIZE;
        this.toggle = toggle;
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

                String[] data = new String[2];
                data[0] = new SimpleDateFormat("MM/dd HH:mm",
                                                Locale.US).format(Calendar.getInstance().getTime());
                data[1] = CompetitiveMode.question + "/" + size + " Solved " +
                        timeFormat((int)TIME);

                if (info.size() > MAX_SIZE) {
                    info.remove(0);
                }
                info.add(data);
                FileHelper.writeData(info, text.getContext());
                customUpTimer.stopTimer();
                resetTimer();
                toggle.setText(R.string.start);
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
        customUpTimer.resetTimer();
        Log.i("CustomTimer", "Timer is getting a reset");
        Toast.makeText(text.getContext(), R.string.reset_timer, Toast.LENGTH_SHORT).show();
    }

    protected String timeFormat(int remTime) {
        StringBuilder timeFormat = new StringBuilder();
        int hours = remTime / (1000*60*60);
        if (hours < 10) {
            timeFormat.append("0").append(hours);
        } else {
            timeFormat.append(hours);
        }

        int minutes = remTime / (1000*60) % 60;
        if (minutes < 10) {
            timeFormat.append(":").append("0").append(minutes);
        } else {
            timeFormat.append(":").append(minutes);
        }

        int seconds = remTime / (1000) % 60;
        if (seconds < 10) {
            timeFormat.append(":").append("0").append(seconds);
        } else {
            timeFormat.append(":").append(seconds);
        }
        return timeFormat.toString();
    }

    public void updateTimer() {
        String timeFormat = timeFormat((int)timeLeft);
        text.setText(timeFormat);
        Log.i("CustomTimer", "Timer view is updating");
    }
}
