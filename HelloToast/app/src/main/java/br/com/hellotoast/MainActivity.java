package br.com.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowCount = findViewById(R.id.counter_textview);

    }


    public void btnToastClick(View view){
        Toast msgToast;
        /*msgToast = Toast.makeText(this, ("Contador..........: " + Integer.toString(mCount)) , Toast.LENGTH_LONG);*/
        msgToast = Toast.makeText(this, (mShowCount.getText().toString()) , Toast.LENGTH_LONG);
        msgToast.show();
    }

    public void btnCountClick(View view){
        String auxContador;
        /*mCount++;
        mShowCount.setText(Integer.toString(mCount));*/
        auxContador = mShowCount.getText().toString();
        auxContador = Integer.toString(Integer.parseInt(auxContador) + 1);
        mShowCount.setText(auxContador);
    }


}
