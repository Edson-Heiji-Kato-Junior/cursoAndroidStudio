package br.com.appbanco.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatacaoMonetaria {

    public static String formatarMoeda(String valor){
        double saldoConvertido = limparMoeda(valor);
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format((saldoConvertido/100));
    }


    public static double limparMoeda (String valor){
        return Double.parseDouble(valor.replaceAll("[^\\d.]", ""));
    }


}
