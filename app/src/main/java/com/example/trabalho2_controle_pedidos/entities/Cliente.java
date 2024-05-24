package com.example.trabalho2_controle_pedidos.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Cliente {
    @PrimaryKey(autoGenerate = true)
    private int clientId;
    private String nome;

    private String telefone;


    public Cliente(){}
    public Cliente(String nome,  String telefone) {
        this.nome = nome;
        this.telefone = telefone;

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nTelefone: " + telefone;
    }
}
