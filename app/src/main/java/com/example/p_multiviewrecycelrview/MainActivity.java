package com.example.p_multiviewrecycelrview;
//目的：同一個資料結構做出不同的itemView,這次簡單實測至少兩組itemView
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.rvView);

        users = new ArrayList<>();
        users.add("titleA");
        users.add("hank");
        users.add("18");
        users.add("carter");
        users.add("22");
        users.add("kobe");
        users.add("35");
        users.add("kidd");
        users.add("45");
        users.add("treey");
        users.add("30");
        users.add("leberon");
        users.add("18");
        users.add("titleB");
        users.add("jennifer");
        users.add("8");

        recyclerAdapter = new RecyclerAdapter(users,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerAdapter);

    }
}