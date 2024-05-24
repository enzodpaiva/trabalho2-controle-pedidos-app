package com.example.trabalho2_controle_pedidos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2_controle_pedidos.entities.Cliente;


import java.util.List;

@Dao
public interface ClienteDAO {
    @Query("SELECT * FROM Cliente WHERE clientId = :id LIMIT 1")
    Cliente getClient(int id);

    @Query("SELECT * FROM Cliente")
    List<Cliente> getAll();

    @Update
    void update(Cliente cliente);

    @Insert
    void insert(Cliente... cliente);

    @Delete
    void delete(Cliente cliente);
}
