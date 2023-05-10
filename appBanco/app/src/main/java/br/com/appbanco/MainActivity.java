package br.com.appbanco;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.appbanco.utils.FormatacaoMonetaria;

public class MainActivity extends AppCompatActivity {

    TextView  txtNome;
    TextView  txtSaldo;
    ImageView ivMostrarSaldo;
    Button    btnAdicionarSaldo;
    Button    btnFazerPix;

    SharedPreferences sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome           = findViewById(R.id.txtvNome);
        txtSaldo          = findViewById(R.id.txtvSaldoValor);
        ivMostrarSaldo    = findViewById(R.id.imvSaldo);
        btnAdicionarSaldo = findViewById(R.id.btnAdicionarSaldo);
        btnFazerPix       = findViewById(R.id.btnFazerPix);

        sharedpref = getSharedPreferences("appBancoPreferences",MODE_PRIVATE);

        txtNome.setText("Edson Heiji Kato Junior");
        txtSaldo.setText(FormatacaoMonetaria.formatarMoeda(sharedpref.getString("app_saldo", "R$ 0,00")));


    }
    /* sempre que for chamar a funcao no on click passar o parametro View view */
    public void esconderSaldo(View view){
        if (ivMostrarSaldo.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.img_saldo_inativo_novo, null).getConstantState()){
            ivMostrarSaldo.setImageResource(R.drawable.ic_action_name);
            txtSaldo.setText(FormatacaoMonetaria.formatarMoeda(sharedpref.getString("app_saldo", "R$ 0,00")));
        }
        else{
                ivMostrarSaldo.setImageResource(R.drawable.img_saldo_inativo_novo);
                txtSaldo.setText("R$ ---,--");
            }
    }

    public void AdicionarSaldo(View view){
        Intent intentAddSaldo = new Intent(this,AdicionarSaldoActivity.class);  // ou Intent intentAddSaldo = new Intent(MainActivity.class, AdicionarSaldoActivity.class);
        //startActivity(intentAddSaldo);  //chamando a nova tela modo antigo
        intentAddSaldo.putExtra("ac_acao", "A");
        adicinarSaldoActivityResultLauncher.launch(intentAddSaldo);
    }

    public void fazerPix(View view){
        Intent intentAddSaldo = new Intent(this,AdicionarSaldoActivity.class);  // ou Intent intentAddSaldo = new Intent(MainActivity.class, AdicionarSaldoActivity.class);
        //startActivity(intentAddSaldo);  //chamando a nova tela modo antigo
        intentAddSaldo.putExtra("ac_acao", "P");
        adicinarSaldoActivityResultLauncher.launch(intentAddSaldo);
    }


    ActivityResultLauncher<Intent> adicinarSaldoActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        txtSaldo.setText(FormatacaoMonetaria.formatarMoeda(sharedpref.getString("app_saldo", "R$ 0,00")));
                    }
                }
            }
    );

}