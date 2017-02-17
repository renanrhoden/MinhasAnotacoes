package com.renanrhoden.minhasanotacoes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    private ImageView salvar;

    private static final String ARQUIVO = "anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (EditText) findViewById(R.id.caixaTextoID);
        salvar = (ImageView) findViewById(R.id.botaoSalvarID);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoDigitado = texto.getText().toString();
                gravarNoArquivo(textoDigitado);

                Toast.makeText(MainActivity.this, "Texto salvo", Toast.LENGTH_SHORT).show();
            }
        });

        if (lerArquivo() != null){
            texto.setText(lerArquivo());
        }

    }

    private void gravarNoArquivo(String textoDigitado) {
        try{
            OutputStreamWriter writer = new OutputStreamWriter( openFileOutput(ARQUIVO, Context.MODE_PRIVATE) );
            writer.write( textoDigitado );
            writer.close();

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }

    }

    private String lerArquivo(){
        String resultado = "";
        try{
            InputStream stream = openFileInput(ARQUIVO);
            if (stream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                String linha = "";

                while ((linha = reader.readLine()) != null){
                    resultado += linha;
                }
            }
            stream.close();
        }catch(IOException e){
            Log.v("MainActivity", e.toString());
        }

        return resultado;

    }
}
