package com.example.trabalho2_controle_pedidos.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabalho2_controle_pedidos.R;
import com.example.trabalho2_controle_pedidos.dao.ClienteDAO;
import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityFuncionarioViewBinding;
import com.example.trabalho2_controle_pedidos.databinding.ActivityPedidoViewBinding;
import com.example.trabalho2_controle_pedidos.entities.Cliente;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;
import com.example.trabalho2_controle_pedidos.entities.Pedido;
import com.example.trabalho2_controle_pedidos.helper.MoneyTextWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PedidoView extends AppCompatActivity {

    private ActivityPedidoViewBinding binding;
    private LocalDatabase db;
    private int dbPedidoId;
    private Pedido dbPedido;
    private Spinner spinnerCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedidoViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbPedidoId = getIntent().getIntExtra(
                "PEDIDO_SELECIONADO_ID", -1);

        spinnerCliente = findViewById(R.id.spinnerCliente);

        // Preencher o Spinner com os nomes dos clientes
        populateClienteSpinner();

        // Adicionar MoneyTextWatcher ao campo de valor total
//        binding.edtValorTotalPedido.addTextChangedListener(new MoneyTextWatcher(binding.edtValorTotalPedido, new Locale("pt", "BR")));
    }

    protected void onResume() {
        super.onResume();
        if (dbPedidoId >= 0) {
            getDBPedido();
        } else {
            binding.btnExcluirPedido.setVisibility(View.GONE);
        }
    }

    private void getDBPedido() {
        dbPedido = db.pedidoModel().getPedido(dbPedidoId);
        binding.edtDescricaoPedido.setText(dbPedido.getDescricaoPedido());
        binding.edtValorTotalPedido.setText(String.valueOf(dbPedido.getValorTotal()));

        // Selecionar o cliente correspondente no Spinner
        List<String> clienteNomes = getClienteNomes();
        int clienteIndex = clienteNomes.indexOf(getClienteNomeById(dbPedido.getClienteId()));
        if (clienteIndex >= 0) {
            spinnerCliente.setSelection(clienteIndex);
        }
    }

    private void populateClienteSpinner() {
        List<String> clienteNomes = getClienteNomes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clienteNomes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCliente.setAdapter(adapter);
    }

    private List<String> getClienteNomes() {
        List<Cliente> clientes = db.clienteModel().getAll();
        List<String> nomes = new ArrayList<>();
        for (Cliente cliente : clientes) {
            nomes.add(cliente.getNome());
        }
        return nomes;
    }

    private String getClienteNomeById(int clienteId) {
        Cliente cliente = db.clienteModel().getClient(clienteId);
        return cliente != null ? cliente.getNome() : null;
    }

    public void salvarPedido(View view) {
        String descricaoPedido = binding.edtDescricaoPedido.getText().toString();
        String valorPedidoStr = binding.edtValorTotalPedido.getText().toString();

        if (descricaoPedido.isEmpty() || valorPedidoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorPedido = Double.parseDouble(valorPedidoStr.replace(",", "."));

        if (spinnerCliente.getSelectedItem() == null) {
            Toast.makeText(this, "Por favor, selecione um cliente.", Toast.LENGTH_SHORT).show();
            return;
        }

        String clienteNome = spinnerCliente.getSelectedItem().toString();
        Cliente cliente = db.clienteModel().getClienteByNome(clienteNome);

        if (cliente == null) {
            Toast.makeText(this, "Cliente não encontrado.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pedido thisPedido = new Pedido(descricaoPedido,valorPedido,cliente.getClientId());

        if (dbPedido != null) {
            thisPedido.setPedidoId(dbPedidoId);
            db.pedidoModel().update(thisPedido);
            Toast.makeText(this, "Pedido atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.pedidoModel().insert(thisPedido);
            Toast.makeText(this, "Pedido criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirPedido(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Pedido")
                .setMessage("Deseja excluir esse pedido?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluir() {
        db.pedidoModel().delete(dbPedido);
        Toast.makeText(this, "Pedido excluído com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View view) {
        finish();
    }
}