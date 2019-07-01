package com.example.healthboss2.modelo;

public class Medico {
    private Integer idMedico;
    private String nomeMedico;
    private String cpfMedico;
    private String crmMedico;
    private Double notaMedico;
    private Integer idEspecialidade;

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCpfMedico() {
        return cpfMedico;
    }

    public void setCpfMedico(String cpfMedico) {
        this.cpfMedico = cpfMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public Double getNotaMedico() {
        return notaMedico;
    }

    public void setNotaMedico(Double notaMedico) {
        this.notaMedico = notaMedico;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    @Override
    public String toString(){
        return nomeMedico;
    }

}