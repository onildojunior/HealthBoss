package com.example.healthboss2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthboss2.dao.DAO;
import com.example.healthboss2.modelo.Paciente;
import com.example.healthboss2.modelo.Consulta;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    String cpf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final String cpf = intent.getStringExtra("cpf");
        this.cpf = cpf;
        final DAO dao = new DAO(this);
        Paciente paciente = dao.buscarCadastroPaciente(cpf);
        //List<Consulta> consultas = dao.buscarConsultas(paciente.getIdPaciente());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            ConsultasFrag fragment = new ConsultasFrag();
            Bundle bundle = new Bundle();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            bundle.putString("cpf",cpf);
            fragment.setArguments(bundle);
            transaction.replace(R.id.fragment_container,fragment).commit();
            navigationView.setCheckedItem(R.id.nav_consultas);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_consultas:
                ConsultasFrag fragment = new ConsultasFrag();
                Bundle bundle = new Bundle();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                bundle.putString("cpf",cpf);
                fragment.setArguments(bundle);
                transaction.replace(R.id.fragment_container,fragment).commit();
                break;
            case R.id.nav_perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PerfilFrag()).commit();
                break;
            case R.id.nav_configuracoes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfigFrag()).commit();
                break;
            case R.id.nav_sobre:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SobreFrag()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
