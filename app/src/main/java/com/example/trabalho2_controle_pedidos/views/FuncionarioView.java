package com.example.trabalho2_controle_pedidos.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.trabalho2_controle_pedidos.R;
import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityFuncionarioViewBinding;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;


public class FuncionarioView extends AppCompatActivity {

    private ActivityFuncionarioViewBinding binding;
    private LocalDatabase db;
    private int dbFuncionarioId;
    private Funcionario dbFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFuncionarioViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        dbFuncionarioId = getIntent().getIntExtra(
                "FUNCIONARIO_SELECIONADO_ID", -1);
    }

    protected void onResume() {
        super.onResume();
        if (dbFuncionarioId >= 0) {
            getDBFuncionario();
        } else {
            binding.btnExcluirFuncionario.setVisibility(View.GONE);
        }
    }

    private void getDBFuncionario() {
        dbFuncionario = db.funcionarioModel().getFunc(dbFuncionarioId);
        binding.edtNomeFuncionario.setText(dbFuncionario.getNome());
        binding.edtTelefoneFuncionario.setText(dbFuncionario.getTelefone());
        binding.edtEmailFuncionario.setText(dbFuncionario.getEmail());
        binding.edtSenhaFuncionario.setText(dbFuncionario.getSenha());
    }

    public void salvarFuncionario(View view) {
        String nomeFuncionario = binding.edtNomeFuncionario.getText().toString();
        String emailFuncionario = binding.edtEmailFuncionario.getText().toString();
        String telefoneFuncionario = binding.edtTelefoneFuncionario.getText().toString();
        String senhaFuncionario = binding.edtSenhaFuncionario.getText().toString();
        if (
                nomeFuncionario.equals("") ||
                emailFuncionario.equals("") ||
                telefoneFuncionario.equals("") ||
                senhaFuncionario.equals("")
        ){
            Toast.makeText(this,"Adicione um funcionario.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Funcionario thisFuncionario = new Funcionario(nomeFuncionario,emailFuncionario,telefoneFuncionario,senhaFuncionario);

        if (dbFuncionario != null) {
            thisFuncionario.setFuncId(dbFuncionarioId);
            db.funcionarioModel().update(thisFuncionario);
            Toast.makeText(this, "Funcionario atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            db.funcionarioModel().insert(thisFuncionario);
            Toast.makeText(this, "Funcionario criado com sucesso.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void excluirFuncionario(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Funcionario")
                .setMessage("Deseja excluir esse funcionario?")
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
        db.funcionarioModel().delete(dbFuncionario);
        Toast.makeText(this, "Funcionario excluído com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void voltar(View view) {
        finish();
    }
}