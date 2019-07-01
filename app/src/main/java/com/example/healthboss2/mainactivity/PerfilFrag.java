package com.example.healthboss2.mainactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthboss2.R;
import com.example.healthboss2.dao.DAO;
import com.example.healthboss2.modelo.Paciente;

public class PerfilFrag extends Fragment {

    private EditText ed_nome_perfil;
    private EditText ed_senha_perfil;
    private EditText ed_endereco_perfil;
    private EditText ed_telefone_perfil;
    private EditText ed_genero_perfil;
    private EditText ed_datanasc_perfil;
    private EditText ed_cpf;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        final String cpf = getArguments().getString("cpf");
        final DAO dao = new DAO(getContext());
        final Paciente paciente = dao.buscarCadastroPaciente(cpf);

        ed_nome_perfil = view.findViewById(R.id.editText1);
        ed_senha_perfil = view.findViewById(R.id.editText2);
        // ed_endereco_perfil = view.findViewById(R.id.editText3);
        ed_telefone_perfil = view.findViewById(R.id.editText4);
        ed_genero_perfil = view.findViewById(R.id.editText5);
        ed_datanasc_perfil = view.findViewById(R.id.editText6);
        ed_cpf = view.findViewById(R.id.editText7);

        preencherPerfil(paciente);

        Button bt_salvar = view.findViewById(R.id.bt_salvar);
        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.alterarPaciente(pegarPerfil(paciente));
                Toast.makeText(getContext(), "Dados alterados", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void preencherPerfil(Paciente paciente) {
        Log.i("Resultado", paciente.getIdPaciente().toString());
        ed_nome_perfil.setText(paciente.getNomePaciente());
        ed_senha_perfil.setText(paciente.getSenhaPaciente());
        ed_telefone_perfil.setText(paciente.getTelefonePaciente());
        ed_genero_perfil.setText(paciente.getGeneroPaciente());
        ed_datanasc_perfil.setText(paciente.getNascimentoPaciente());
        ed_cpf.setText(paciente.getCpfPaciente());
        ed_cpf.setFocusable(false);
    }

    public Paciente pegarPerfil(Paciente paciente) {
        Log.i("Resultado", paciente.getIdPaciente().toString());
        paciente.setNomePaciente(ed_nome_perfil.getText().toString());
        paciente.setSenhaPaciente(ed_senha_perfil.getText().toString());
        paciente.setTelefonePaciente(ed_telefone_perfil.getText().toString());
        paciente.setGeneroPaciente(ed_genero_perfil.getText().toString());
        paciente.setNascimentoPaciente(ed_datanasc_perfil.getText().toString());
        return paciente;
    }
}
