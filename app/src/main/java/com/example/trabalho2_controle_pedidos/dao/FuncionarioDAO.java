package com.example.trabalho2_controle_pedidos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2_controle_pedidos.entities.Funcionario;

import java.util.List;

@Dao
public interface FuncionarioDAO {
    @Query("SELECT * FROM Funcionario WHERE funcId = :id LIMIT 1")
    Funcionario getFunc(int id);

    @Query("SELECT * FROM Funcionario")
    List<Funcionario> getAll();

    @Update
    void update(Funcionario funcionario);

    @Insert
    void insert(Funcionario... funcionario);

    @Delete
    void delete(Funcionario funcionario);
}
