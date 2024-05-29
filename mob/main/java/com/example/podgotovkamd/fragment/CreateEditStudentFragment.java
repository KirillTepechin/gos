package com.example.podgotovkamd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.podgotovkamd.R;
import com.example.podgotovkamd.Student;

public class CreateEditStudentFragment extends Fragment {

    private EditText editTextFio;
    private EditText editTextGroup;
    private Button btnAdd;
    private int editPos =-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_edit_fragment, container, false);

        editTextFio = view.findViewById(R.id.editTextFio);
        editTextGroup = view.findViewById(R.id.editTextGroup);
        btnAdd = view.findViewById(R.id.buttonAdd);

        Bundle bundleFromMain = this.getArguments();

        if(bundleFromMain!=null){
            Student student = (Student) bundleFromMain.getSerializable("student");
            editTextFio.setText(student.getFio());
            editTextGroup.setText(student.getGroup());
            editPos = bundleFromMain.getInt("pos");
        }

        btnAdd.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Student student = new Student(editTextFio.getText().toString(), editTextGroup.getText().toString());
            bundle.putSerializable("student", student);

            if(!student.getGroup().isEmpty() && !student.getFio().isEmpty()){
                //On edit
                if(editPos!=-1){
                    bundle.putInt("pos", editPos);
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, MainFragment.class, bundle)
                        .commit();
            }
            else{
                Toast.makeText(getActivity(), "Заполните поля", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
}
