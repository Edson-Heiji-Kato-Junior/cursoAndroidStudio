package br.com.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import br.com.appbanco.utils.FormatacaoMonetaria;

public class AdicionarSaldoActivity extends AppCompatActivity {

    EditText edtAddSaldo;

    String acao = "A";

    Button btnAdicionarSubtrair;

    TextView txtvAdicionarSubtrair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_saldo);

        Intent intent = getIntent();
        acao = intent.getStringExtra("ac_acao");
        Log.d("Modo Tela:", acao);

        edtAddSaldo = findViewById(R.id.edtAdicionarSaldo);

        btnAdicionarSubtrair = findViewById(R.id.btnAdicionarSaldo_actvAS);
        txtvAdicionarSubtrair = findViewById(R.id.txtvAdicionarSaldo);

        if (acao.equals("A")){
            btnAdicionarSubtrair.setText("Adicionar Saldo");
            txtvAdicionarSubtrair.setText("ADICIONAR SALDO");
        }
        else{
            btnAdicionarSubtrair.setText("Fazer Pix");
            txtvAdicionarSubtrair.setText("FAZER PIX");
        }


        edtAddSaldo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence saldo, int i, int i1, int i2) {

                edtAddSaldo.removeTextChangedListener(this); //desabilita o compotamento listener padrao do edit, senão ele fica se atrapalhando

                //String saldoFormatado  = NumberFormat.getCurrencyInstance().format((saldo));
                String saldoFormatado  = FormatacaoMonetaria.formatarMoeda(edtAddSaldo.getText().toString());

                edtAddSaldo.setText(saldoFormatado);
                edtAddSaldo.setSelection(saldoFormatado.length());

                edtAddSaldo.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void calcularSaldo(View view){

        SharedPreferences sharedPref = getSharedPreferences("appBancoPreferences",MODE_PRIVATE);

        double saldoAnterior = FormatacaoMonetaria.limparMoeda(sharedPref.getString("app_saldo", "0.00"));

        double saldoFinal = 0;

        double auxSaldo = FormatacaoMonetaria.limparMoeda(edtAddSaldo.getText().toString());

        if (acao.equals("A")) {
            saldoFinal = saldoAnterior + FormatacaoMonetaria.limparMoeda(edtAddSaldo.getText().toString());
        }
        else{
            if(auxSaldo < saldoAnterior){
                saldoFinal = saldoAnterior - FormatacaoMonetaria.limparMoeda(edtAddSaldo.getText().toString());
            }
            else{
                Toast toast = Toast.makeText(this, "Valor Maior que o saldo!!!", 10000);
                toast.show();
                saldoFinal = saldoAnterior;
            }
        }




        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("app_saldo", Double.toString(saldoFinal));
        editor.apply();


        //finish(); metodo antigo - chamada mais simples, quando não é necessário usar o result - usar abaixo
        Intent intentRetorno = new Intent();
        setResult(RESULT_OK, intentRetorno);
        super.onBackPressed();
    }


}