package com.example.trabalho2_controle_pedidos.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabalho2_controle_pedidos.R;
import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityFuncionarioListBinding;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;
import com.example.trabalho2_controle_pedidos.views.FuncionarioView;

import java.util.List;

public class FuncionarioList extends AppCompatActivity {
    private ActivityFuncionarioListBinding binding;
    private LocalDatabase db;
    private List<Funcionario> funcionarios;
    private ListView listViewFuncionarios;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFuncionarioListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewFuncionarios = binding.listFuncionarios;

        binding.btnHomeFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FuncionarioList.this, FuncionarioView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, FuncionarioView.class);
        preencheMarcas();
    }
    private void preencheMarcas() {
        funcionarios = db.funcionarioModel().getAll();
        FuncionarioAdapter funcionariosAdapter = new FuncionarioAdapter(this, funcionarios);
//        ArrayAdapter<Funcionario> funcionariosAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, funcionarios);
        listViewFuncionarios.setAdapter((ListAdapter) funcionariosAdapter);

        listViewFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Funcionario marcaselecionada = funcionarios.get(position);
                edtIntent.putExtra("FUNCIONARIO_SELECIONADO_ID",
                        marcaselecionada.getFuncId());
                startActivity(edtIntent);
            }
        });
    }
}