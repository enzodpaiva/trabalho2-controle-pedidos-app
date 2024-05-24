package com.example.trabalho2_controle_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}