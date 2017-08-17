package com.example.readingcsv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AlunoActivity extends AppCompatActivity{
    /** Declarado meu objeto tipo File no escopo da classe, tornando o mesmo acessível de atividades no método onCreate.
     *  O método onCreate "cria" minha activity mostrada ao usuário. Basicamente mescla um layout XML com
     *  código Java para que seja mostrada a informação para o cliente. **/
    File arquivo = new File(Environment.getExternalStorageDirectory() + File.separator + "Chamada.csv");
    /** Declarado meu objeto inteiro, começando em zero, para que seja percorrido meu array de alunos passado pela
     *  intent vinda da MainActivity.class **/
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_aluno);
        /** O objeto intent é então inicializado com seu getIntent, que captura as intents enviadas para essa activity.  **/
        Intent intent = getIntent();
        /** Como possuía um arraylist de alunos, fora passado pela intent o mesmo arraylist.
         *  O método getStringArrayListExtra() solicita no parâmetro a atividade de onde vem a intent e seu identificador na
         *  aplicação. Veja o código da MainActivity.
         * **/
        final ArrayList<String> arrayAlunos = intent.getStringArrayListExtra(MainActivity.MEU_ALUNO);
        /** As views declaradas logo abaixo são todas criadas através do editor de layout gráfico.
         *  Para que eu possa manipulá-las, é necessário: instanciar um objeto com o tipo de View solicitada, seu nome referência
         *  que recebe então o tipo de View e o método findViewById. Este último solicita por parâmetro a id do nosso objeto XML
         *  criado pelo Layout Edit. R = resources . id = ids no XML . nome_da_id;
         * **/
            final TextView viewNomeAluno = (TextView) findViewById(R.id.nome_aluno);
            final Button btnPresente = (Button) findViewById(R.id.buttonPresente);
            final Button btnAusente = (Button) findViewById(R.id.buttonAusente);
        /** setTextSize define o tamanho programaticamente do meu textView. **/
            viewNomeAluno.setTextSize(30);
        /** setText define o conteúdo programaticamente do meu view. Na linha abaixo, defino o text da view como o array de alunos
         *  vindo pela intent da MainActivity na posição i = 0. **/
            viewNomeAluno.setText(arrayAlunos.get(i));

            final ControlCSV controlOf = new ControlCSV();
        final View.OnClickListener meuClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        arquivo.createNewFile();
                        FileWriter myFileWriter = controlOf.myFileWriter(arquivo, true);
                        PrintWriter myPrintWriter = controlOf.myPrintWriter(myFileWriter);
                        myPrintWriter.write(viewNomeAluno.getText().toString() + ";" + arrayAlunos +"\n");
                        i++;
                        viewNomeAluno.setText(arrayAlunos.get(i));
                        myPrintWriter.close();
                        myFileWriter.close();
                    }catch(Exception e){

                    }
                }
            };
            btnAusente.setOnClickListener(meuClick);
            btnPresente.setOnClickListener(meuClick);
    }
}
