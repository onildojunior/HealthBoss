package com.example.healthboss2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthboss2.dao.DAO;
import com.example.healthboss2.modelo.Especialidade;
import com.example.healthboss2.modelo.Medico;
import com.example.healthboss2.modelo.Paciente;
import com.example.healthboss2.modelo.Consulta;


import java.util.List;

public class MarcarConsulta extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerEspecialidades;
    private Spinner spinnerMedicos;
    private DAO dao;
    private String cpf;
    private int idEspecialidade;
    private int idMedico;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marcar_consulta);
        Intent intent = getIntent();
        final String cpf = intent.getStringExtra("cpf");
        final DAO dao = new DAO(this);
        this.cpf = cpf;
        this.dao = dao;
        final Paciente paciente = dao.buscarCadastroPaciente(cpf);
        List<Especialidade> especialidades = dao.buscarEspecialidade();

        //Mostrar especialidades no spinner
        spinnerEspecialidades = findViewById(R.id.seleciona_especialidade_marcar_consulta);
        spinnerEspecialidades.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, especialidades);
        spinnerEspecialidades.setAdapter(adapter);

        spinnerMedicos = findViewById(R.id.seleciona_medico_marcar_consulta);

        Button bt_marcar_consulta = findViewById(R.id.button_marcar_consulta);
        bt_marcar_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Medico medico = dao.buscarMedicoNomeEspecialidade(spinnerMedicos.getSelectedItem().toString(),spinnerEspecialidades.getSelectedItemPosition());
                Consulta consulta = new Consulta();
                consulta.setIdMedico(medico.getIdMedico());
                consulta.setIdPaciente(paciente.getIdPaciente());
                dao.agendarConsulta(consulta);
                finish();
                Toast.makeText(MarcarConsulta.this, "Consulta agendada!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        idEspecialidade = pos;
        List<Medico> medicos = dao.buscarMedico(idEspecialidade);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, medicos);
        spinnerMedicos.setAdapter(adapter);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
