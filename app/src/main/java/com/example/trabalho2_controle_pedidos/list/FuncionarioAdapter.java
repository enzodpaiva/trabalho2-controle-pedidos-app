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
import com.example.trabalho2_controle_pedidos.entities.Funcionario;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;


public class FuncionarioAdapter extends ArrayAdapter<Funcionario> {

    private Context mContext;
    private List<Funcionario> funcionariosList;

    public FuncionarioAdapter(@NonNull Context context, List<Funcionario> list) {
        super(context, 0 , list);
        mContext = context;
        funcionariosList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Funcionario currentFuncionario = funcionariosList.get(position);

//        ShapeableImageView image = listItem.findViewById(R.id.image);
        // Configure a imagem aqui, se houver

        TextView nome = listItem.findViewById(R.id.nome);
        nome.setText(currentFuncionario.getNome());

        TextView email = listItem.findViewById(R.id.email);
        email.setText(currentFuncionario.getEmail());

        TextView telefone = listItem.findViewById(R.id.telefone);
        telefone.setText(currentFuncionario.getTelefone());

        return listItem;
    }
}