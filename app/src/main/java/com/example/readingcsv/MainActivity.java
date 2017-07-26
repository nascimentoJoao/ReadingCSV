package com.example.readingcsv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(getAssets().open("labbancodados.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CSVReader reader = new CSVReader((inputReader), ';');
        try {
            reader.readNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] line;

        try {
            while ((line = reader.readNext()) != null){
                switch(line[0]){
                    case "Ano": line[1] = "2018";
                        break;
                    case "Aluno": line[0] = "ALUNOS";
                                    line[1] = "\n";
                        break;

                }
                TextView textView = new TextView(this);
                textView.setTextSize(30);
                textView.setText(line[0] +" "+ line[1]);
                ViewGroup layout = (ViewGroup) findViewById(R.id.content_csv);
                layout.addView(textView);
            }





        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
