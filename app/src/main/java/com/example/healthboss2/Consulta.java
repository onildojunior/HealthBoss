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

public class Consulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        final DAO dao = new DAO(this);

        Button bt_pross = findViewById(R.id.bt_pross);
        bt_pross.setOnClickListener(new View.OnClickListener() {
            EditText ed_cpf = findViewById(R.id.ed_cpf);

            @Override
            public void onClick(View v) {
                String cpf = ed_cpf.getText().toString();
                if (ValidaCPF.isCPF(cpf)) {
                    Paciente paciente = dao.buscarCadastroPaciente(cpf);

//                    Log.i("resultado", paciente.getIdPaciente().toString());
                    if (paciente.getIdPaciente() != null) {
                        Intent intent = new Intent(Consulta.this, Login.class);
//                        finish();
                        intent.putExtra("cpf", cpf);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Consulta.this, Cadastro.class);
//                        finish();
                        intent.putExtra("cpf", cpf);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Consulta.this, "CPF inválido!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
