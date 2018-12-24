package com.example.mylibraryand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {
    public static final String joke_list = "5";
    RecyclearAdapter recyclearAdapter;
    RecyclerView recyclerView;
    ArrayList<String> joke;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        intent = getIntent();
        joke = new ArrayList<>();
        joke = intent.getStringArrayListExtra("joke_list");


        recyclerView = findViewById(R.id.joke_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclearAdapter = new RecyclearAdapter(this,joke);
        recyclerView.setAdapter(recyclearAdapter);
    }
}
