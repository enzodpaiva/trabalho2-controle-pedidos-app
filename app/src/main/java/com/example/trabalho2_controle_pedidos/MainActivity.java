package com.example.trabalho2_controle_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.trabalho2_controle_pedidos.R;
import com.example.trabalho2_controle_pedidos.dao.FuncionarioDAO;
import com.example.trabalho2_controle_pedidos.database.LocalDatabase;
import com.example.trabalho2_controle_pedidos.databinding.ActivityMainBinding;
import com.example.trabalho2_controle_pedidos.list.ClienteList;
import com.example.trabalho2_controle_pedidos.list.FuncionarioList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");
        binding.userNameTextView.setText(userName.toString());

        binding.btnFuncionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this, FuncionarioList.class);
                startActivity(it);
            }
        });
        binding.btnClientes.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v) {
                   Intent it= new Intent(MainActivity.this, ClienteList.class);
                   startActivity(it);
           }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logoutIntent);
                finish();
            }
        });
    }
}