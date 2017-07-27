package com.example.readingcsv;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.opencsv.CSVReader;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

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
        Calendar dateObject = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = formatDate.format(dateObject.getTime());
        TextView showData = (TextView) findViewById(R.id.showData);
        showData.setText(dataAtual);
        try {

            while ((line = reader.readNext()) != null){
                switch(line[0]){
                    case "Disciplina":
                        String disciplina = line[1];
                        TextView showDisciplina = (TextView) findViewById(R.id.showDisciplina);
                        showDisciplina.setText(disciplina);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
