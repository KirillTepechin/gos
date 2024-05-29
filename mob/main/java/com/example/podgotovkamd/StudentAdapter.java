package com.example.podgotovkamd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podgotovkamd.fragment.CreateEditStudentFragment;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;
    private boolean withButton;
    private DBHelper dbHelper;

    public StudentAdapter(List<Student> students,DBHelper dbHelper, boolean withButton) {
        this.students = students;
        this.withButton = withButton;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        if (!withButton) {
            Button button = view.findViewById(R.id.btnChange);
            button.setVisibility(View.GONE);

            Button buttonDel = view.findViewById(R.id.btnDelete);
            buttonDel.setVisibility(View.GONE);
        }
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.fio.setText(student.getFio());
        holder.group.setText(student.getGroup());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView fio;
        TextView group;

        public StudentViewHolder(View view) {
            super(view);
            fio = view.findViewById(R.id.textViewFio);
            group = view.findViewById(R.id.textViewGroup);
            Button button = view.findViewById(R.id.btnChange);
            if(button!=null){
                button.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Student student = students.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("student", student);
                        bundle.putInt("pos", position);
                        ((AppCompatActivity)view.getContext())
                                .getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frgmCont, CreateEditStudentFragment.class, bundle)
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }

            Button buttonDel = view.findViewById(R.id.btnDelete);
            if(buttonDel!=null){
                buttonDel.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Student student = students.get(position);
                        students.remove(position);
                        notifyItemRemoved(position);
                        dbHelper.deleteStudent(student);
                    }
                });
            }
        }
    }
}
