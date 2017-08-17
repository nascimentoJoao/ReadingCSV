package com.example.readingcsv;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Joao Nascimento on 04/08/2017.
 */

public class DataAtual {

    private String DATA_ATUAL;

    public DataAtual(){
        Calendar objetoCalendar = Calendar.getInstance();
        SimpleDateFormat formatadorDeData = new SimpleDateFormat("dd/MM/yyyy");
        DATA_ATUAL = formatadorDeData.format(objetoCalendar.getTime());
    }

    public String getDataAtual(){
        return DATA_ATUAL;
    }
}
