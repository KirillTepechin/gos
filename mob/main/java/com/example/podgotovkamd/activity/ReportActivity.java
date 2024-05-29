package com.example.podgotovkamd.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podgotovkamd.R;
import com.example.podgotovkamd.Student;
import com.example.podgotovkamd.StudentAdapter;

import java.util.List;

public class ReportActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);

        List<Student> students = (List<Student>) getIntent().getSerializableExtra("students");

        recyclerView = findViewById(R.id.recyclerViewReport);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentAdapter studentAdapter = new StudentAdapter(students, null, false);
        recyclerView.setAdapter(studentAdapter);
    }
}
