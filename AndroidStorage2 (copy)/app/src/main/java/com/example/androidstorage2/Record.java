package com.example.androidstorage2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Record extends AppCompatActivity {

    private static final String TAG = "hello";
    List<ModelClass> modelClassList = new ArrayList<>();
    RecyclerView recView;
    Adapter mAdapter;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recView = findViewById(R.id.recyclerView);


        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recView.setLayoutManager(layoutManager);

        Intent i = getIntent();
        if((List<Employee>) i.getSerializableExtra("emplist") != null){

        List<Employee> emplist = (List<Employee>) i.getSerializableExtra("emplist");

        for(Employee emp1: emplist){
            int id = emp1.getId();
            String name = emp1.getName();
            String address = emp1.getAddress();
            String mobile = emp1.getMobile();
            modelClassList.add(new ModelClass(name,mobile,address));
        }

        }
        
        mAdapter = new Adapter(modelClassList);
        recView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
