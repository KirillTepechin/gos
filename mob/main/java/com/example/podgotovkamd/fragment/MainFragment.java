package com.example.podgotovkamd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podgotovkamd.DBHelper;
import com.example.podgotovkamd.JsonData;
import com.example.podgotovkamd.R;
import com.example.podgotovkamd.Student;
import com.example.podgotovkamd.StudentAdapter;
import com.example.podgotovkamd.activity.ReportActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button btnReport;
    private Button btnAdd;
    private Button btnImport;
    private Button btnExport;
    private EditText editText;
    DBHelper dbHelper;
    private List<Student> students = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        btnReport = view.findViewById(R.id.btnReport);
        btnAdd = view.findViewById(R.id.btnAddStudent);
        editText = view.findViewById(R.id.edit_query);
        btnImport = view.findViewById(R.id.btnImport);
        btnExport = view.findViewById(R.id.btnExport);


        dbHelper = new DBHelper(getActivity());
        Bundle bundle = this.getArguments();

        students.clear();
        students.addAll(dbHelper.getAllStudents());

        if(bundle!=null){
            //edit
            if(bundle.getInt("pos", -1)!=-1){
                Student student = students.get(bundle.getInt("pos"));
                student.setFio(((Student) bundle.getSerializable("student")).getFio());
                student.setGroup(((Student) bundle.getSerializable("student")).getGroup());
                dbHelper.updateStudent(student);
            }
            //add
            else{
                Student student = (Student) bundle.getSerializable("student");
                students.add(student);
                dbHelper.addStudent(student);
            }
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .detach(MainFragment.this)
                    .attach(MainFragment.this)
                    .commit();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StudentAdapter adapter = new StudentAdapter(students, dbHelper, true);
        recyclerView.setAdapter(adapter);

        btnReport.setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), ReportActivity.class);
            intent.putExtra("students", (Serializable) students.stream()
                    .filter(student -> student.getGroup().equalsIgnoreCase(editText.getText().toString().trim()))
                    .collect(Collectors.toList()));
            startActivity(intent);
        });

        btnAdd.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frgmCont, CreateEditStudentFragment.class, null)
                .addToBackStack(null)
                .commit());

        btnExport.setOnClickListener(v -> {
            JsonData.saveStudents(students);
            Toast.makeText(getActivity(), "Экспортировано", Toast.LENGTH_SHORT).show();
        });

        btnImport.setOnClickListener(v -> {
            students.clear();
            students.addAll(JsonData.getStudents());
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Импортировано", Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Bundle bundle = this.getArguments();

        if(bundle!=null){
            setArguments(null);
        }
    }
}
