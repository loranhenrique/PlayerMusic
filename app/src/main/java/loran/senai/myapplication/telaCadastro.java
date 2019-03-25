package loran.senai.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class telaCadastro extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha;
    private Button btCadastrar, btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btCadastrar = findViewById(R.id.btCadastrar);
        btCancelar = findViewById(R.id.btCancelar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoDeDados bd = new BancoDeDados(getApplicationContext(), 1);

                if(bd.cadastraUsuario(etNome.getText().toString(),
                        etEmail.getText().toString(),
                        etSenha.getText().toString())){
                    Toast.makeText(getApplicationContext(),
                            "Dados cadastrado!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Dados não cadastrados!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(i);
            }
        });
    }
}
