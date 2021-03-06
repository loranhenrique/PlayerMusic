package loran.senai.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private Button btCadastrar, btAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etSenha = findViewById(R.id.etSenha);
        btAcessar = findViewById(R.id.btAcessar);
        btCadastrar = findViewById(R.id.btCadastrar);

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoDeDados bd = new BancoDeDados(getApplicationContext(), 1);

                if(bd.verificaAcesso(etUsuario.getText().toString(),
                        etSenha.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Acesso liberado!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), telaAplicativo.class);

                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Acesso negado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), telaCadastro.class);

                startActivity(i);
            }
        });
    }
}
