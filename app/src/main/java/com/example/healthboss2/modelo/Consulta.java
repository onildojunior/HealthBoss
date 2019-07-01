package com.example.healthboss2.modelo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.healthboss2.dao.DAO;

public class Consulta {

    private Integer idConsulta;
    private String dataConsulta;
    private String horaConsulta;
    private Double precoConsulta;
    private String procedimentoConsulta;
    private Integer idPaciente;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Integer idMedico;

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public Double getPrecoConsulta() {
        return precoConsulta;
    }

    public void setPrecoConsulta(Double precoConsulta) {
        this.precoConsulta = precoConsulta;
    }

    public String getProcedimentoConsulta() {
        return procedimentoConsulta;
    }

    public void setProcedimentoConsulta(String procedimentoConsulta) {
        this.procedimentoConsulta = procedimentoConsulta;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }


    @Override
    public String toString(){
        DAO dao = new DAO(context);
        Medico medico = dao.buscarMedicoID(idMedico);
        Especialidade especialidade = dao.buscarNomeEspecialidade(medico.getIdEspecialidade()+1);
        return especialidade.getNomeEspecialidade() + " - " + medico.getNomeMedico();
    }
}
