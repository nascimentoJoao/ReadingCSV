package com.example.readingcsv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        BufferedReader reader = new BufferedReader(inputReader);
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = reader.readLine()) != null){
                TextView textView = new TextView(this);
                textView.setTextSize(30);
                textView.setText(line);
                TableLayout layout = (TableLayout) findViewById(R.id.content_csv);
                layout.addView(textView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
