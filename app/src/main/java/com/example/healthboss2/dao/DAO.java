package com.example.healthboss2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.healthboss2.modelo.Consulta;
import com.example.healthboss2.modelo.Especialidade;
import com.example.healthboss2.modelo.Medico;
import com.example.healthboss2.modelo.Paciente;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "healthbossbd";
    private static final int DATABASE_VERSION = 4;

    //Cria a Tabela Plano de Saude
    private static final String DATABASE_CREATE_PLANO_DE_SAUDE = "CREATE TABLE " +
            "plano_de_saude (idPlanodeSaude INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nomePlanodeSaude TEXT NOT NULL, " +
            "telefonePlanodeSaude TEXT);";

    //Cria a Tabela Paciente
    private static final String DATABASE_CREATE_PACIENTE = "CREATE TABLE " +
            "paciente (idPaciente INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nomePaciente TEXT NOT NULL, " +
            "cpfPaciente TEXT NOT NULL, " +
            "nascimentoPaciente TEXT, " +
            "telefonePaciente TEXT, " +
            "generoPaciente TEXT, " +
            "senhaPaciente TEXT NOT NULL, " +
            "idPlanodeSaude INTEGER, " +
            "FOREIGN KEY (idPlanodeSaude) REFERENCES plano_de_saude(idPlanodeSaude));";


    private static final String DATABASE_CREATE_DIAGNOSTICO = "CREATE TABLE " +
            "diagnostico (idDiagnostico INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "dataDiagnostico TEXT, " +
            "procedimentoDiagnostico TEXT, " +
            "idPaciente INTEGER NOT NULL, " +
            "FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente));";


    private static final String DATABASE_CREATE_ESPECIALIDADE = "CREATE TABLE " +
            "especialidade (idEspecialidade INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nomeEspecialidade TEXT NOT NULL);";

    private static final String DATABASE_CREATE_MEDICO = "CREATE TABLE " +
            "medico (idMedico INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nomeMedico TEXT NOT NULL, " +
            "cpfMedico TEXT NOT NULL, " +
            "crmMedico TEXT NOT NULL, " +
            "notaMedico REAL, " +
            "idEspecialidade INTEGER NOT NULL, " +
            "FOREIGN KEY (idEspecialidade) REFERENCES especialidade(idEspecialidade));";

    //Cria a Tabela Consulta
    private static final String DATABASE_CREATE_CONSULTA = "CREATE TABLE consulta ("
            + "idConsulta INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "dataConsulta TEXT, "
            + "horaConsulta TEXT, "
            + "precoConsulta REAL, "
            + "procedimentoConsulta TEXT, "
            + "idMedico INTEGER NOT NULL, "
            + "idPaciente INTEGER NOT NULL, "
            + "FOREIGN KEY (idMedico) REFERENCES Medico (idMedico), "
            + "FOREIGN KEY (idPaciente) REFERENCES Paciente (idPaciente));";


    private static final String DATABASE_CREATE_CONSULTORIO = "CREATE TABLE consultorio (" +
            "idConsultorio INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nomeConsultorio TEXT NOT NULL, " +
            "enderecoConsultorio TEXT NOT NULL, " +
            "telefoneConsultorio TEXT);";

    private static final String DATABASE_CREATE_CONSULTORIO_ESPECIALIDADE = "CREATE TABLE " +
            "consultorio_especialidade (" +
            "idConsultorio INTEGER NOT NULL, " +
            "idEspecialidade INTEGER NOT NULL, " +
            "FOREIGN KEY (idConsultorio) REFERENCES consultorio (idConsultorio), " +
            "FOREIGN KEY (idEspecialidade) REFERENCES especialidade(idEspecialidade)," +
            "PRIMARY KEY (idConsultorio, idEspecialidade));";

    private static final String DATABASE_CREATE_CONSULTORIO_MEDICO = "CREATE TABLE " +
            "consultorio_medico (" +
            "idConsultorio INTEGER NOT NULL, " +
            "idMedico INTEGER NOT NULL, " +
            "FOREIGN KEY (idConsultorio) REFERENCES Consultorio(idConsultorio), " +
            "FOREIGN KEY (idMedico) REFERENCES Paciente(idMedico), " +
            "PRIMARY KEY (idConsultorio, idMedico));";

    private static final String DATABASE_CREATE_PLANO_DE_SAUDE_MEDICO = "CREATE TABLE plano_de_saude_medico ("
            + "idPlanodeSaude INTEGER NOT NULL, "
            + "idMedico INTEGER NOT NULL, "
            + "FOREIGN KEY (idPlanodeSaude) REFERENCES plano_de_saude(idPlanodeSaude), "
            + "FOREIGN KEY (idMedico) REFERENCES Medico(idMedico), "
            + "PRIMARY KEY (idPlanodeSaude, idMedico));";

    private static final String DATABASE_CREATE_CONSULTORIO_PLANO_DE_SAUDE =
            "CREATE TABLE consultorio_plano_de_saude(" +
                    "idConsultorio INTEGER NOT NULL, " +
                    "idPlanodeSaude INTEGER NOT NULL, " +
                    "FOREIGN KEY (idConsultorio) REFERENCES consultorio (idConsultorio), " +
                    "FOREIGN KEY (idPlanodeSaude) REFERENCES plano_de_saude (idPlanodeSaude), " +
                    "PRIMARY KEY (idConsultorio, idPlanodeSaude));";


    private static final String DATABASE_INSERT_ESPECIALIDADES =
            "INSERT INTO especialidade (nomeEspecialidade) " +
                    "VALUES ('Ortopedia'), " +
                    "('Cardiologia'), " +
                    "('Endrocnologia'), " +
                    "('Odontologia'), " +
                    "('Oftalmologia')," +
                    "('Pediatria'), " +
                    "('Urologia'), " +
                    "('Gastroenterologia'), " +
                    "('Neurologia');";

    private static final String DATABASE_INSERT_MEDICOS =
            "INSERT INTO medico (nomeMedico, cpfMedico, crmMedico, notaMedico, idEspecialidade) " +
                    "VALUES " +
                    "('Rancho Chruts','12345678978','7343972390/MA','10','0')," +
                    "('Onildo Ninho','13134222078','7343972391/MA','10','1'), " +
                    "('Falcio Silva','06700680042','876357/MA','10','1'), " +
                    "('Lucas Coxinha', '49945057057', '8343972393/MA', '10', '3'), " +
                    "('Miru Yhan','11277833001','7834972394/MA','10','4'), " +
                    "('Light Yagami', '38685103037', '8343972390/MA', '10', '5'), " +
                    "('Pusheen Almeida','10558197035','7834342390/MA','10','6'), " +
                    "('Antônio Figueiredo', '80235972053', '9516243/MA', '10', '7'), " +
                    "('Joel Sarmento','47178507004','32498472/MA','10','8'), " +
                    "('Erica Fagundes', '38003308062', '73972390/MA', '9.8', '1'), " +
                    "('Janaína Riod', '17481266018', '48372390/MA', '10', '1'), " +
                    "('Margarida Jardins', '81282998080', '18372390/MA', '7.8', '1'), " +
                    "('Alexandra Tavares', '68197574090', '78322390/MA', '10', '3'), " +
                    "('Francisca Bacabeira', '44978417074', '54654987/MA', '9.4', '3'), " +
                    "('Vitória do Mearim', '52366175027', '1564874/MA', '10', '8'), " +
                    "('Juliene Duarte', '87279727042', '2187987/MA', '8.8', '5'), " +
                    "('Alexi Lallas','86041495034','357545/MA','7.6','4'), " +
                    "('Amaury Júnior', '50458912000', '219873527/MA', '9.0', '8'), " +
                    "('Valita Mondial', '13521276015', '21698798/MA', '9.5', '6'), " +
                    "('Djalma Dutra', '84230498098', '8516354/MA', '8.4', '7');";


    public DAO(Context context) {
        super(context, "Consulta", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PLANO_DE_SAUDE);
        db.execSQL(DATABASE_CREATE_CONSULTORIO);
        db.execSQL(DATABASE_CREATE_ESPECIALIDADE);
        db.execSQL(DATABASE_CREATE_PACIENTE);
        db.execSQL(DATABASE_CREATE_MEDICO);
        db.execSQL(DATABASE_CREATE_CONSULTA);
        db.execSQL(DATABASE_CREATE_DIAGNOSTICO);
        db.execSQL(DATABASE_CREATE_CONSULTORIO_ESPECIALIDADE);
        db.execSQL(DATABASE_CREATE_CONSULTORIO_MEDICO);
        db.execSQL(DATABASE_CREATE_CONSULTORIO_PLANO_DE_SAUDE);
        db.execSQL(DATABASE_CREATE_PLANO_DE_SAUDE_MEDICO);

        db.execSQL(DATABASE_INSERT_ESPECIALIDADES);
        db.execSQL(DATABASE_INSERT_MEDICOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delPlanodeSaude = "DROP TABLE IF EXISTS plano_de_saude";
        String delPaciente = "DROP TABLE IF EXISTS paciente";
        String delConsultorio = "DROP TABLE IF EXISTS consultorio";
        String delEspecialidade = "DROP TABLE IF EXISTS especialidade";
        String delMedico = "DROP TABLE IF EXISTS medico";
        String delConsulta = "DROP TABLE IF EXISTS consulta";
        String delDiagnostico = "DROP TABLE IF EXISTS diagnostico";
        String delConsultorioEspecialidade = "DROP TABLE IF EXISTS consultorio_especialidade";
        String delConsultorioMedico = "DROP TABLE IF EXISTS consultorio_medico";
        String delConsultorioPlanodeSaude = "DROP TABLE IF EXISTS consultorio_plano_de_saude";
        String delPlanodeSaudeMedico = "DROP TABLE IF EXISTS plano_de_saude_medico";

        db.execSQL(delPlanodeSaude);
        db.execSQL(delPaciente);
        db.execSQL(delConsultorio);
        db.execSQL(delEspecialidade);
        db.execSQL(delMedico);
        db.execSQL(delConsulta);
        db.execSQL(delDiagnostico);
        db.execSQL(delConsultorioEspecialidade);
        db.execSQL(delConsultorioMedico);
        db.execSQL(delConsultorioPlanodeSaude);
        db.execSQL(delPlanodeSaudeMedico);

        onCreate(db);

    }

    public void cadastrarPaciente(Paciente paciente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesPaciente(paciente);
        db.insert("paciente", null, dados);

    }

    public void cadastrarEspecialidade(Especialidade especialidade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesEspecialidade(especialidade);
        db.insert("especialidade", null, dados);

    }

    public void cadastrarMedico(Medico medico) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesMedico(medico);
        db.insert("medico", null, dados);
    }

    public void alterarPaciente(Paciente paciente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesPaciente(paciente);
        String id[] = {paciente.getIdPaciente().toString()};
        db.update("paciente", dados, "idPaciente = ?", id);

    }

    public void agendarConsulta(Consulta consulta) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesConsulta(consulta);
        db.insert("consulta", null, dados);
    }

    public Especialidade buscarNomeEspecialidade (Integer idEspecialidade) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {idEspecialidade.toString()};
        Cursor c = db.rawQuery("SELECT * FROM especialidade WHERE idEspecialidade = ?",params);
        Especialidade especialidade = new Especialidade();
        if (c.moveToNext()) {
            especialidade.setNomeEspecialidade(c.getString(c.getColumnIndex("nomeEspecialidade")));
        }
        return especialidade;
    }

    public Medico buscarMedicoNomeEspecialidade(String nomeMedico, Integer idEspecialidade) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {nomeMedico, idEspecialidade.toString()};
        Cursor c = db.rawQuery("SELECT * FROM medico WHERE nomeMedico = ? and idEspecialidade = ?", params);
        Medico medico = new Medico();

        if (c.moveToNext()) {
            medico.setIdMedico(c.getInt(c.getColumnIndex("idMedico")));
            medico.setNomeMedico(c.getString(c.getColumnIndex("nomeMedico")));
            medico.setCpfMedico(c.getString(c.getColumnIndex("cpfMedico")));
            medico.setCrmMedico(c.getString(c.getColumnIndex("crmMedico")));
            medico.setNotaMedico(c.getDouble(c.getColumnIndex("notaMedico")));
            medico.setIdEspecialidade(c.getInt(c.getColumnIndex("idEspecialidade")));
        }
        return medico;
    }

    public Paciente buscarCadastroPaciente(String cpfPaciente) {
        SQLiteDatabase db = getReadableDatabase();
        String[] cpf = {cpfPaciente};
        Cursor c = db.rawQuery("SELECT * FROM paciente WHERE cpfPaciente = ?;", cpf);

        Paciente paciente = new Paciente();
        if (c.moveToNext()) {
            paciente.setIdPaciente(c.getInt(c.getColumnIndex("idPaciente")));
            paciente.setNomePaciente(c.getString(c.getColumnIndex("nomePaciente")));
            paciente.setCpfPaciente(c.getString(c.getColumnIndex("cpfPaciente")));
            paciente.setNascimentoPaciente(c.getString(c.getColumnIndex("nascimentoPaciente")));
            paciente.setTelefonePaciente(c.getString(c.getColumnIndex("telefonePaciente")));
            paciente.setGeneroPaciente(c.getString(c.getColumnIndex("generoPaciente")));
            paciente.setSenhaPaciente(c.getString(c.getColumnIndex("senhaPaciente")));
            paciente.setIdPlanodeSaude(c.getString(c.getColumnIndex("idPlanodeSaude")));
        }
        c.close();
        return paciente;
    }

    public Medico buscarMedicoID(Integer idMedico) {
        SQLiteDatabase db = getReadableDatabase();
        String[] cpf = {idMedico.toString()};
        Cursor c = db.rawQuery("SELECT * FROM medico WHERE idMedico = ?;", cpf);

        Medico medico = new Medico();
        if (c.moveToNext()) {
            medico.setIdMedico(c.getInt((c.getColumnIndex("idMedico"))));
            medico.setNomeMedico(c.getString(c.getColumnIndex("nomeMedico")));
            medico.setCpfMedico(c.getString(c.getColumnIndex("cpfMedico")));
            medico.setCrmMedico(c.getString(c.getColumnIndex("crmMedico")));
            medico.setNotaMedico(c.getDouble(c.getColumnIndex("notaMedico")));
            medico.setIdEspecialidade(c.getInt(c.getColumnIndex("idEspecialidade")));
        }
        c.close();
        return medico;
    }

    public List<Especialidade> buscarEspecialidade() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM especialidade;", null);

        List<Especialidade> especialidades = new ArrayList<>();
        while (c.moveToNext()) {
            Especialidade especialidade = new Especialidade();
            especialidade.setIdEspecialidade(c.getInt((c.getColumnIndex("idEspecialidade"))));
            especialidade.setNomeEspecialidade(c.getString(c.getColumnIndex("nomeEspecialidade")));

            especialidades.add(especialidade);
        }
        c.close();
        return especialidades;
    }

    public List<Medico> buscarMedico(Integer idEspecialidade) {
        SQLiteDatabase db = getReadableDatabase();
        String id[] = {idEspecialidade.toString()};
        Cursor c = db.rawQuery("SELECT * FROM medico WHERE idEspecialidade = ?;", id);

        List<Medico> medicos = new ArrayList<>();
        while (c.moveToNext()) {
            Medico medico = new Medico();
            medico.setIdMedico(c.getInt((c.getColumnIndex("idMedico"))));
            medico.setNomeMedico(c.getString(c.getColumnIndex("nomeMedico")));
            medico.setCpfMedico(c.getString(c.getColumnIndex("cpfMedico")));
            medico.setCrmMedico(c.getString(c.getColumnIndex("crmMedico")));
            medico.setNotaMedico(c.getDouble(c.getColumnIndex("notaMedico")));
            medico.setIdEspecialidade(c.getInt(c.getColumnIndex("idEspecialidade")));

            medicos.add(medico);
        }
        c.close();
        return medicos;
    }

    public List<Consulta> buscarConsultas(Integer idPaciente, Context context) {
        SQLiteDatabase db = getReadableDatabase();
        String id[] = {idPaciente.toString()};
        Cursor c = db.rawQuery("SELECT * FROM consulta WHERE idPaciente = ? ORDER BY idConsulta DESC;", id);

        List<Consulta> consultas = new ArrayList<>();
        while (c.moveToNext()) {
            Consulta consulta = new Consulta();
            consulta.setContext(context);
            consulta.setIdConsulta(c.getInt(c.getColumnIndex("idConsulta")));
            consulta.setDataConsulta(c.getString(c.getColumnIndex("dataConsulta")));
            consulta.setHoraConsulta(c.getString(c.getColumnIndex("horaConsulta")));
            consulta.setProcedimentoConsulta(c.getString(c.getColumnIndex("procedimentoConsulta")));
            consulta.setPrecoConsulta(c.getDouble(c.getColumnIndex("precoConsulta")));
            consulta.setIdMedico(c.getInt(c.getColumnIndex("idMedico")));
            consulta.setIdPaciente(c.getInt(c.getColumnIndex("idPaciente")));

            consultas.add(consulta);
        }
        c.close();
        return consultas;
    }

    private ContentValues getContentValuesPaciente(Paciente paciente) {
        ContentValues dados = new ContentValues();
        dados.put("nomePaciente", paciente.getNomePaciente());
        dados.put("cpfPaciente", paciente.getCpfPaciente());
        dados.put("nascimentoPaciente", paciente.getNascimentoPaciente());
        dados.put("telefonePaciente", paciente.getTelefonePaciente());
        dados.put("generoPaciente", paciente.getGeneroPaciente());
        dados.put("senhaPaciente", paciente.getSenhaPaciente());
        dados.put("idPlanodeSaude", paciente.getIdPlanodeSaude());

        return dados;
    }

    private ContentValues getContentValuesConsulta(Consulta consulta) {
        ContentValues dados = new ContentValues();
        dados.put("dataConsulta", consulta.getDataConsulta());
        dados.put("horaConsulta", consulta.getHoraConsulta());
        dados.put("precoConsulta", consulta.getPrecoConsulta());
        dados.put("procedimentoConsulta", consulta.getProcedimentoConsulta());
        dados.put("idPaciente", consulta.getIdPaciente());
        dados.put("idMedico", consulta.getIdMedico());

        return dados;
    }

    private ContentValues getContentValuesEspecialidade(Especialidade especialidade) {
        ContentValues dados = new ContentValues();
        dados.put("nomeEspecialidade", especialidade.getNomeEspecialidade());
        return dados;
    }

    private ContentValues getContentValuesMedico(Medico medico) {
        ContentValues dados = new ContentValues();
        dados.put("nomeMedico", medico.getNomeMedico());
        dados.put("cpfMedico", medico.getCpfMedico());
        dados.put("crmMedico", medico.getCrmMedico());
        dados.put("notaMedico", medico.getNotaMedico());
        dados.put("idEspecialidade", medico.getIdEspecialidade());

        return dados;
    }


}