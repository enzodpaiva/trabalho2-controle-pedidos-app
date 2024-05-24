package com.example.trabalho2_controle_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.trabalho2_controle_pedidos.dao.FuncionarioDAO;
import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LocalDatabase myDb;
    private FuncionarioDAO funDao;
    public static boolean isAllowed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb= Room.databaseBuilder(this, LocalDatabase.class,"Funcionario").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        funDao=myDb.funcionarioModel();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.emailEditText.getText().toString();
                String senha=binding.passwordEditText.getText().toString();
                if (funDao.login(email,senha)){
                    String nome = funDao.getNomeByEmail(email); // Obtenha o nome do usuário do banco de dados
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USER_NAME", nome.toString());
                    startActivity(intent);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this,"Login invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


    }
}