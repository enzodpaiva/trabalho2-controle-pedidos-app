package com.example.trabalho2_controle_pedidos.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Cliente.class,
        parentColumns = "clientId",
        childColumns = "clienteId",
        onDelete = ForeignKey.CASCADE
))
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    private int pedidoId;
    private String descricaoPedido;
    private double valorTotal;
    private int clienteId;

    public Pedido() {}

    public Pedido(String descricaoPedido, double valorTotal, int clienteId) {
        this.descricaoPedido = descricaoPedido;
        this.valorTotal = valorTotal;
        this.clienteId = clienteId;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }

    public void setDescricaoPedido(String descricaoPedido) {
        this.descricaoPedido = descricaoPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }


}
