package com.example.trabalho2_controle_pedidos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trabalho2_controle_pedidos.entities.Pedido;

import java.util.List;

@Dao
public interface PedidoDAO {
    @Query("SELECT * FROM Pedido WHERE pedidoId = :id LIMIT 1")
    Pedido getPedido(int id);

    @Query("SELECT * FROM Pedido")
    List<Pedido> getAll();

    @Update
    void update(Pedido pedido);

    @Insert
    void insert(Pedido... pedido);

    @Delete
    void delete(Pedido pedido);

    @Query("SELECT COUNT(*) FROM Pedido WHERE clienteId = :clienteId")
    int getCountByClienteId(int clienteId);
}
