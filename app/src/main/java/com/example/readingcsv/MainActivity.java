package com.example.readingcsv;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.List;
import java.util.StringTokenizer;

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
                        break;

                    case "Aluno":

                        final List<String> meusAlunos = new ArrayList<String>();

                        while((line = reader.readNext()) != null) {
                            meusAlunos.add(line[0]);
                        }
                        ArrayAdapter<String> alunos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meusAlunos);
                        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
                        listaAlunos.setAdapter(alunos);
                        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                int pos = position;
                                if (pos == meusAlunos.indexOf(meusAlunos)){
                                    Intent intent = new Intent("android.intent.action.show_aluno.class");
                                    startActivity(intent);
                                }
                            }
                        });

                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
