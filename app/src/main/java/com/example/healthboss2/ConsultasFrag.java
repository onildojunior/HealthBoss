package com.example.healthboss2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConsultasFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


         final String cpf = getArguments().getString("cpf");


        View view = inflater.inflate(R.layout.fragment_consultas, container, false);

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
}
