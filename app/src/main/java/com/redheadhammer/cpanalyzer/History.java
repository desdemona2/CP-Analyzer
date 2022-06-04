package com.redheadhammer.cpanalyzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView github;
    ArrayList<String[]> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("History");
        setContentView(R.layout.history_activity);

        github = findViewById(R.id.github);
        recyclerView = findViewById(R.id.history_recycler);

        info = FileHelper.readData(History.this);

        /* adding bottom bar functions */
        @SuppressWarnings("unused") BottomBar bottomBar = new BottomBar(History.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(History.this));
        HistoryAdapter historyAdapter = new HistoryAdapter(History.this, info);
        recyclerView.setAdapter(historyAdapter);

        github.setOnClickListener(this::openUrl);
    }
    public void openUrl(View view) {
        /* get url from tag */
        String url = (String) view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        /* pass the url to intent data */
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}