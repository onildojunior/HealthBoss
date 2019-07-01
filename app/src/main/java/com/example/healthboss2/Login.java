package com.example.healthboss2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthboss2.dao.DAO;
import com.example.healthboss2.mainactivity.MainActivity;
import com.example.healthboss2.modelo.Paciente;

public class Login extends AppCompatActivity {

    final DAO dao = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText ed_senha_login = findViewById(R.id.ed_senha_login);

        Intent intent = getIntent();
        final String cpf = intent.getStringExtra("cpf");
        final EditText ed_cpf_cad = findViewById(R.id.ed_cpf_login);
        ed_cpf_cad.setText(cpf);

        Button bt_ent = findViewById(R.id.bt_ent);
        bt_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senhaFormulario = ed_senha_login.getText().toString();
                Paciente paciente = dao.buscarCadastroPaciente(ed_cpf_cad.getText().toString());

                String[] dados ;
                dados = new String[2];
                dados[0] = ed_cpf_cad.getText().toString();
                dados[1] = senhaFormulario;

                Log.i("Resultado", dados[0]);
                Log.i("Resultado", dados[1]);

                if (ValidaCPF.isCPF(dados[0]) && validaDados(paciente, dados)) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("cpf", cpf);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "CPF ou senha incorreto!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validaDados(Paciente paciente, String[] dados) {
        return paciente.getCpfPaciente().equals(dados[0]) && paciente.getSenhaPaciente().equals(dados[1]);
    }
}
