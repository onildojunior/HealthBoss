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
import com.example.healthboss2.modelo.Paciente;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        final EditText ed_nome_cadastro = findViewById(R.id.ed_nome_cadastro);
        final EditText ed_cpf_cadastro = findViewById(R.id.ed_cpf_cadastro);
        final EditText ed_senha_cadastro = findViewById(R.id.ed_senha_cadastro);
        final EditText ed_confirma = findViewById(R.id.ed_confirma);
        Intent intent = getIntent();
        String cpf = intent.getStringExtra("cpf");
        ed_cpf_cadastro.setText(cpf);
        ed_cpf_cadastro.setFocusable(false);

        final DAO dao = new DAO(this);

        Button bt_cad = findViewById(R.id.bt_cad);
        bt_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente paciente = new Paciente();

                String nome = ed_nome_cadastro.getText().toString();
                String cpf = ed_cpf_cadastro.getText().toString();
                String senha = ed_senha_cadastro.getText().toString();
                String confirma = ed_confirma.getText().toString();

                paciente.setNomePaciente(nome);
                paciente.setCpfPaciente(cpf);
                paciente.setSenhaPaciente(senha);

                dao.cadastrarPaciente(paciente);

                if (senha.equals(confirma)) {
                    Intent intent = new Intent(Cadastro.this, Login.class);
                    finish();
                    intent.putExtra("cpf", cpf);
                    Toast.makeText(Cadastro.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(Cadastro.this, "Senhas não correspondem!", Toast.LENGTH_LONG).show();
                }
                Log.i("Resultado", paciente.getCpfPaciente());
                Log.i("Resultado", paciente.getNomePaciente());
                Log.i("Resultado", paciente.getSenhaPaciente());
            }
        });
    }
}
