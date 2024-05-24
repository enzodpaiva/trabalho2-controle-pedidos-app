package com.example.trabalho2_controle_pedidos.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityClientViewBinding;
import com.example.trabalho2_controle_pedidos.entities.Cliente;

public class ClientView extends AppCompatActivity {

    private ActivityClientViewBinding binding;
    private LocalDatabase db;
    private int dbClienteId;
    private Cliente dbCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbClienteId = getIntent().getIntExtra(
                "CLIENTE_SELECIONADO_ID", -1);
    }

    protected void onResume() {
        super.onResume();
        if (dbClienteId >= 0) {
            getDBCliente();
        } else {
            binding.btnExcluirCliente.setVisibility(View.GONE);
        }
    }

    private void getDBCliente() {
        dbCliente = db.clienteModel().getClient(dbClienteId);
        binding.edtNomeCliente.setText(dbCliente.getNome());
        binding.edtTelefoneCliente.setText(dbCliente.getTelefone());

    }

    public void salvarCliente(View view) {
        String nomeCliente = binding.edtNomeCliente.getText().toString();

        String telefoneCliente = binding.edtTelefoneCliente.getText().toString();

        if (
                nomeCliente.equals("") ||

                        telefoneCliente.equals("")

        ){
            Toast.makeText(this,"Adicione um cliente.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente thisCliente = new Cliente(nomeCliente,telefoneCliente);

        if (dbCliente != null) {
            thisCliente.setClientId(dbClienteId);
            db.clienteModel().update(thisCliente);
            Toast.makeText(this, "Cliente atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.clienteModel().insert(thisCliente);
            Toast.makeText(this, "Cliente criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirCliente(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Cliente")
                .setMessage("Deseja excluir esse cliente?")
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
        db.clienteModel().delete(dbCliente);
        Toast.makeText(this, "Cliente excluído com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}