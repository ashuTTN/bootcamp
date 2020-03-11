package com.example.androidstorage2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText address = findViewById(R.id.address);
        final EditText mobile = findViewById(R.id.mobile);
        final EditText name = findViewById(R.id.name);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recreate DB in case of conflict due to cache/backup

                EmployeeDatabase db = Room.databaseBuilder(getApplicationContext(),
                        EmployeeDatabase.class, "employee-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                Employee emp = new Employee();
                List<Employee> emplist = db.employeeDao().getAll();

                switch (v.getId()) {
                    case (R.id.addEmployee):
                        emp.setName(name.getText().toString());
                        emp.setMobile(mobile.getText().toString());
                        emp.setAddress(address.getText().toString());
                        db.employeeDao().insert(emp);
                        emplist = db.employeeDao().getAll();
                        Intent intent = new Intent(getBaseContext(), Record.class);
                        intent.putExtra("emplist", (Serializable) emplist);
                        startActivity(intent);
                        break;
                    case (R.id.showDb):
                        Intent intent1 = new Intent(getBaseContext(), Record.class);
                        intent1.putExtra("emplist", (Serializable) emplist);
                        startActivity(intent1);
                        break;
                }
            }
        };
        Button addEmployee = findViewById(R.id.addEmployee);
        addEmployee.setOnClickListener(listener);
        Button showDb = findViewById(R.id.showDb);
        showDb.setOnClickListener(listener);
    }
}