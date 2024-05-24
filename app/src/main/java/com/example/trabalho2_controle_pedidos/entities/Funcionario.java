package com.example.trabalho2_controle_pedidos.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Funcionario")
public class Funcionario {
    @PrimaryKey(autoGenerate = true)
    private int funcId;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    public Funcionario(){}
    public Funcionario(String nome, String email, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone;
    }
}
