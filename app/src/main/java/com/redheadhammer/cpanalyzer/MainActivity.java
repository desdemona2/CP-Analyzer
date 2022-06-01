package com.redheadhammer.cpanalyzer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private int totalTime;
    private int questions;
    ImageView cancel;
    TextInputEditText time, question_size;
    Button defaultValues, submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_values);

        /* attach views with its ui-elements */
        cancel = findViewById(R.id.cancel);
        time = findViewById(R.id.time_input);
        question_size = findViewById(R.id.total_questions);

        defaultValues = findViewById(R.id.default_values);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this::onSubmit);
        cancel.setOnClickListener(this::cancel);
        defaultValues.setOnClickListener(this::cancel);
    }

    public void onSubmit(View view) {
        try {
            totalTime = Integer.parseInt(String.valueOf(time.getText()))*60000;
            questions = Integer.parseInt(String.valueOf(question_size.getText()));
        } catch (NumberFormatException e) {
            totalTime = 10_800_000;
            questions  = 7;
            Toast.makeText(this, R.string.invalid_values,
                    Toast.LENGTH_LONG).show();
        }
        startActivity();
    }
    private void startActivity(){
        Intent intent = new Intent(this,
                CompetitiveMode.class);
        intent.putExtra("totalTime", totalTime);
        intent.putExtra("questions", (Integer)questions);
        this.startActivity(intent);
    }

    public void cancel(View view){
        totalTime = 10_800_000;
        questions  = 7;
        startActivity();
    }
}