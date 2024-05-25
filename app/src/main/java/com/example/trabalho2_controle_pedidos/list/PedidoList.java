package com.example.trabalho2_controle_pedidos.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.trabalho2_controle_pedidos.databinding.ActivityPedidoListBinding;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;
import com.example.trabalho2_controle_pedidos.entities.Pedido;
import com.example.trabalho2_controle_pedidos.views.FuncionarioView;
import com.example.trabalho2_controle_pedidos.views.PedidoView;

import java.util.List;

public class PedidoList extends AppCompatActivity {

    private ActivityPedidoListBinding binding;
    private LocalDatabase db;
    private List<Pedido> pedidos;
    private ListView listViewPedidos;
    private Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedidoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewPedidos = binding.listPedidos;

        binding.btnHomePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PedidoList.this, PedidoView.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, PedidoView.class);
        preenchePedidos();
    }

    private void preenchePedidos() {
        pedidos = db.pedidoModel().getAll();
        PedidoAdapter pedidosAdapter = new PedidoAdapter(this, pedidos,db.clienteModel());
//        ArrayAdapter<Funcionario> funcionariosAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, funcionarios);
        listViewPedidos.setAdapter((ListAdapter) pedidosAdapter);

        listViewPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Pedido pedidosSelecionado = pedidos.get(position);
                edtIntent.putExtra("PEDIDO_SELECIONADO_ID",
                        pedidosSelecionado.getPedidoId());
                startActivity(edtIntent);
            }
        });
    }
}