package com.example.podgotovkamd;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonData {
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    public static List<Student> getStudents(){
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/data.json");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            fileReader.close();
            Student[] mcArray = gson.fromJson(stringBuilder.toString(), Student[].class);
            return  Arrays.asList(mcArray);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void saveStudents(List<Student> students){
        String jsonString = gson.toJson(students);

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/data.json");

        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonString);
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
