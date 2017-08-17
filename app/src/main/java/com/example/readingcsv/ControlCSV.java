package com.example.readingcsv;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Joao Nascimento on 15/08/2017.
 */

public class ControlCSV {

    public File access() {
        File arquivo = new File(Environment.getExternalStorageDirectory() + File.separator + "Chamada.csv");
        return arquivo;
    }

    public FileWriter myFileWriter(File file, boolean bool) throws IOException {
        FileWriter write = new FileWriter(file, bool);
        return write;
    }

    public PrintWriter myPrintWriter(FileWriter fw) throws IOException{
        PrintWriter printWriter = new PrintWriter(fw);
        return printWriter;
    }
}
