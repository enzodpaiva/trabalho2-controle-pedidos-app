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
import com.example.trabalho2_controle_pedidos.entities.Cliente;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;


public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private Context mContext;
    private List<Cliente> clientesList;

    public ClienteAdapter(@NonNull Context context, List<Cliente> list) {
        super(context, 0 , list);
        mContext = context;
        clientesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_client,parent,false);

        Cliente currentCliente = clientesList.get(position);

//        ShapeableImageView image = listItem.findViewById(R.id.image);
        // Configure a imagem aqui, se houver

        TextView nome = listItem.findViewById(R.id.nome);
        nome.setText(currentCliente.getNome());

        TextView telefone = listItem.findViewById(R.id.telefone);
        telefone.setText(currentCliente.getTelefone());

        return listItem;
    }
}