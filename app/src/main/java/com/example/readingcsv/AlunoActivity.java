package com.example.readingcsv;

import android.content.Context;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class AlunoActivity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_aluno);
        Intent intent = getIntent();

        final ArrayList<String> arrayAlunos = intent.getStringArrayListExtra(MainActivity.MEU_ALUNO);

        final TextView statusAluno = (TextView) findViewById(R.id.status_aluno);

        final TextView viewNomeAluno = (TextView) findViewById(R.id.nome_aluno);
        viewNomeAluno.setTextSize(30);

        Button next = (Button) findViewById(R.id.next);
        Button prev = (Button) findViewById(R.id.prev);

            viewNomeAluno.setText(arrayAlunos.get(i));

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        statusAluno.setText("");
                         i++;
                         viewNomeAluno.setText(arrayAlunos.get(i));
                    }catch(ArrayIndexOutOfBoundsException e){
                        viewNomeAluno.setText("NAAAO");
                    }
                }
            });
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        statusAluno.setText("");
                        i--;
                        viewNomeAluno.setText(arrayAlunos.get(i));
                    }catch (ArrayIndexOutOfBoundsException e){
                        viewNomeAluno.setText("JA FALEI QUE NAO!");
                    }

                }
            });

        String filename = "myfile";
        String message = "Olá mundo";

        FileOutputStream outputStream;

        statusAluno.setTextSize(30);


        Button buttonPresente = (Button) findViewById(R.id.buttonPresente);
        buttonPresente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView statusAluno = (TextView) findViewById(R.id.status_aluno);
                statusAluno.setText("Presente");
            }
        });

        Button buttonAusente = (Button) findViewById(R.id.buttonAusente);
        buttonAusente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView statusAluno = (TextView) findViewById(R.id.status_aluno);
                statusAluno.setText("Ausente");
            }
        });



        try {
            String path = /**"/sdcard/Download"**/ Environment.getExternalStorageDirectory().getPath()+"/Download/";

            File file = new File(path, filename);
            String mensagem = "HEY JOE";
            Log.e("DIRETORIO", path);
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(mensagem.getBytes());
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
 public int AdicionaUm(int i){
     i++;
     return i;
 }
}
