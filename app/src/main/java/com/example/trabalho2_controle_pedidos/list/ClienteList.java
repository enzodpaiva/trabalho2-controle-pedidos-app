package com.example.trabalho2_controle_pedidos.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityClientListBinding;
import com.example.trabalho2_controle_pedidos.entities.Cliente;
import com.example.trabalho2_controle_pedidos.views.ClientView;

import java.util.List;

public class ClienteList extends AppCompatActivity {
    private ActivityClientListBinding binding;
    private LocalDatabase db;
    private List<Cliente> clientes;
    private ListView listViewClientes;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClientListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewClientes = binding.listClientes;

        binding.btnHomeCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClienteList.this, ClientView.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, ClientView.class);
        preencheMarcas();
    }
    private void preencheMarcas() {
        clientes= db.clienteModel().getAll();
        ClienteAdapter clienteAdapter = new ClienteAdapter(this, clientes);
//        ArrayAdapter<Funcionario> funcionariosAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, funcionarios);
        listViewClientes.setAdapter((ListAdapter) clienteAdapter);

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Cliente marcaselecionada = clientes.get(position);
                edtIntent.putExtra("CLIENTE_SELECIONADO_ID",
                        marcaselecionada.getClientId());
                startActivity(edtIntent);
            }
        });
    }
}