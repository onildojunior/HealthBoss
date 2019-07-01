package com.example.healthboss2.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthboss2.modelo.Consulta;
import com.example.healthboss2.MarcarConsulta;
import com.example.healthboss2.R;
import com.example.healthboss2.dao.DAO;
import com.example.healthboss2.modelo.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ConsultasFrag extends Fragment {
    DAO dao;
    private ListView listaConsultas;
    private Paciente paciente;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final String cpf = getArguments().getString("cpf");
        dao = new DAO(getContext());
        View view = inflater.inflate(R.layout.fragment_consultas, container, false);
        Log.i("Resultado consulta",cpf);
        paciente = dao.buscarCadastroPaciente(cpf);

        listaConsultas = view.findViewById(R.id.lista_consultas);
        registerForContextMenu(listaConsultas);
        criaLista();

        //Botão de marcar consulta
        final FloatingActionButton mostrarEspecialidades = view.findViewById(R.id.fab);
        mostrarEspecialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MarcarConsulta.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        criaLista();
    }

    public void criaLista(){
        List<Consulta> consultas = dao.buscarConsultas(paciente.getIdPaciente(),getContext());
        ArrayAdapter<Consulta> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, consultas);
        listaConsultas.setAdapter(adapter);

    }
}
