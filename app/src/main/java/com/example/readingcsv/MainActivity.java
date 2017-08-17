package com.example.readingcsv;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    public final static String MEU_ALUNO = "com.example.readingcsv.ALUNO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissao = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissao != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

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
        DataAtual data = new DataAtual();
        final TextView showData = (TextView) findViewById(R.id.showData);
        showData.setText(" "+data.getDataAtual());
        showData.setTextSize(20);
        try {

            while ((line = reader.readNext()) != null){
                switch(line[0]){
                    case "Disciplina":
                        String disciplina = line[1];
                        TextView showDisciplina = (TextView) findViewById(R.id.showDisciplina);
                        showDisciplina.setText(" "+disciplina);
                        showDisciplina.setTextSize(20);
                        break;
                    case "Aluno":
                        final ArrayList<String> meusAlunos = new ArrayList<String>();
                        while((line = reader.readNext()) != null) {
                                meusAlunos.add(line[0]);
                        }
                        Collections.sort(meusAlunos);
                        Button startChamada = (Button) findViewById(R.id.start_chamada);
                        startChamada.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent showAluno = new Intent(MainActivity.this, AlunoActivity.class);
                                showAluno.putStringArrayListExtra(MEU_ALUNO, meusAlunos);
                                startActivity(showAluno);
                            }
                        });
                        final ArrayAdapter<String> alunos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meusAlunos);
                        final ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
                        listaAlunos.setAdapter(alunos);
                        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(MainActivity.this, AlunoActivity.class);
                                Integer indexOfAluno = Integer.valueOf(position);
                                String passaAluno = meusAlunos.get(indexOfAluno);
                                intent.putExtra(MEU_ALUNO, passaAluno);
                                startActivity(intent);
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
