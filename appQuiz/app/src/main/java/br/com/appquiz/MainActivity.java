package br.com.appquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.Thread;

import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

/* ****************** Declarando Variáveis ********************* */
    TextView txtPergunta, txtResposta;
    RadioGroup  grupoResposta;
    RadioButton opcaoA;
    RadioButton opcaoB;
    RadioButton opcaoC;

    String perguntas[] = {
            "Qual era a cor do Cavalo Branco de Napoleão?",
            "Com Quantos Pau, se faz uma Canoa?",
            "2 + 2 x (1 + 1) ",
            "2 + 6 * 2",
            "1 + 1",
    };

    String labelsOpcaoA[] = {
            "Alazão.",
            "10",
            "6",
            "16",
            "1"
    };


    String labelsOpcaoB[] = {
            "Castanho.",
            "1",
            "16",
            "14",
            "1"

    };
    String labelsOpcaoC[] = {
            "Branco.",
            "2",
            "10",
            "10",
            "2"
    };

    int respostasCorretas[] = {0,1,0,1,2}; /* 0 = radiobutton0, 1 = radiobutton1, 2 = radiobutton2,*/

    int[] respostasUsuario = new int[perguntas.length];  /* 0 = radiobutton0, 1 = radiobutton1, 2 = radiobutton2,*/

    int i =0;

    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* buscar um componente em outro form = findViewById(R.id.nome do componente) */
        txtPergunta   = findViewById(R.id.tvPergunta);
        txtResposta   = findViewById(R.id.txtVResposta);
        grupoResposta = findViewById(R.id.rdResposta);
        opcaoA        = findViewById(R.id.rbOpcaoA  );
        opcaoB        = findViewById(R.id.rbOpcaoB  );
        opcaoC        = findViewById(R.id.rbOpcaoC  );
        btnEnviar     = findViewById(R.id.btnEnviar);
        btnEnviar.callOnClick(); //ou atualizaPerguntas(btnEnviar);

        grupoResposta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int opcao) {
                switch (opcao){
                    case R.id.rbOpcaoA:
                        Log.d("TESTE", "Opção A");
                        respostasUsuario[i-1] = 0;
                        break;
                    case R.id.rbOpcaoB:
                        Log.d("TESTE", "Opção B");
                        respostasUsuario[i-1] = 1;
                        break;
                    case R.id.rbOpcaoC:
                        Log.d("TESTE", "Opção C");
                        respostasUsuario[i-1] = 2;
                        break;
                }
            }
        });

    }

    public void atualizaPerguntas(View view){
        grupoResposta.clearCheck();
            if (i < (perguntas.length)){
                txtPergunta.setText(perguntas[i]);
                opcaoA.setText(labelsOpcaoA[i]);
                opcaoB.setText(labelsOpcaoB[i]);
                opcaoC.setText(labelsOpcaoC[i]);
                i++;
            }
            else if (i == (perguntas.length)) {
                conferirResultado();
                i = 0;
                btnEnviar.callOnClick();
            }
    }

    public void conferirResultado(){
        int acertos = 0;
        int erros   = 0;
        for (int j = 0; j < perguntas.length; j++) {
            if (respostasUsuario[j] == respostasCorretas[j]){
                acertos++;
                Log.d("Validação", (j + 1) + " correta");

            }
            else{
                erros++;
                Log.d("Validação", (j + 1) + " errada");
            }
        }
        txtResposta.setText("Total de Acertos: "+ Integer.toString(acertos) + "   Total de Erros     : " + Integer.toString(erros));
        alerta("Resultado!", "Você acertou " + acertos + "perguntas!" );
        txtResposta.setText("");
    }

    public void alerta(String titulo, String mensagem){
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(mensagem);
        alertDialog.show();
    }

}