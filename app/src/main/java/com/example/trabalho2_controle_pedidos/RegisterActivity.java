package com.example.trabalho2_controle_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.trabalho2_controle_pedidos.dao.FuncionarioDAO;
import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityRegisterBinding;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private LocalDatabase myDb;
    private FuncionarioDAO funDao;
    public static boolean isAllowed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb= Room.databaseBuilder(this, LocalDatabase.class,"Funcionario").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        funDao=myDb.funcionarioModel();

        binding.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email=s.toString();
                if (funDao.isTaken(email)){
                    isAllowed=false;
                    Toast.makeText(RegisterActivity.this, "Já cadastrado!", Toast.LENGTH_SHORT).show();
                }else{
                    isAllowed=true;
                }
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllowed){
                        String nomeFuncionario = binding.nameEditText.getText().toString();
                        String emailFuncionario = binding.emailEditText.getText().toString();
                        String telefoneFuncionario = binding.phoneEditText.getText().toString();
                        String senhaFuncionario = binding.passwordEditText.getText().toString();
                        if (
                                nomeFuncionario.equals("") ||
                                        emailFuncionario.equals("") ||
                                        telefoneFuncionario.equals("") ||
                                        senhaFuncionario.equals("")
                        ){
                            Toast.makeText(RegisterActivity.this,"Adicione um funcionario.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Funcionario thisFuncionario = new Funcionario(nomeFuncionario,emailFuncionario,telefoneFuncionario,senhaFuncionario);
                        funDao.insert(thisFuncionario);
                        Toast.makeText(RegisterActivity.this, "Funcionario criado com sucesso.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Já cadastrado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}