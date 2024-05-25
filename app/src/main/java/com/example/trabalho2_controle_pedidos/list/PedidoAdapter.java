package com.example.trabalho2_controle_pedidos.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalho2_controle_pedidos.R;
import com.example.trabalho2_controle_pedidos.dao.ClienteDAO;
import com.example.trabalho2_controle_pedidos.entities.Cliente;
import com.example.trabalho2_controle_pedidos.entities.Pedido;

import java.util.List;

public class PedidoAdapter extends ArrayAdapter<Pedido> {
    private Context mContext;
    private List<Pedido> pedidosList;
    private ClienteDAO clienteDAO;

    public PedidoAdapter(@NonNull Context context, List<Pedido> list, ClienteDAO clienteDAO) {
        super(context,0,list);
        mContext=context;
        pedidosList=list;
        this.clienteDAO = clienteDAO;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_pedido, parent, false);
        }

        Pedido currentPedido = pedidosList.get(position);

        // Find and set the descricao_pedido TextView
        TextView descricaoPedidoTextView = listItem.findViewById(R.id.descricao_pedido);
        if (descricaoPedidoTextView != null) {
            descricaoPedidoTextView.setText(currentPedido.getDescricaoPedido());
        }

        // Find and set the valor_total_pedido TextView
        TextView valorTotalPedidoTextView = listItem.findViewById(R.id.valor_total_pedido);
        if (valorTotalPedidoTextView != null) {
            valorTotalPedidoTextView.setText(String.valueOf(currentPedido.getValorTotal()));
        }

        TextView cliente = listItem.findViewById(R.id.cliente);
        // Fetch the client name using the clienteId
        Cliente clienteEntity = clienteDAO.getClient(currentPedido.getClienteId());
        if (clienteEntity != null) {
            cliente.setText(clienteEntity.getNome());
        } else {
            cliente.setText("Cliente não encontrado");
        }

        return listItem;
    }
}
