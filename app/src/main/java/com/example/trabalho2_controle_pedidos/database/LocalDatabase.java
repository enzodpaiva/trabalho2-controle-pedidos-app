package com.example.trabalho2_controle_pedidos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trabalho2_controle_pedidos.dao.FuncionarioDAO;
import com.example.trabalho2_controle_pedidos.entities.Funcionario;

@Database(entities = {Funcionario.class}, version = 2)
public abstract class LocalDatabase extends RoomDatabase{
    private static LocalDatabase INSTANCE;
    public static LocalDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class,"ControlePediidos").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract FuncionarioDAO funcionarioModel();
}
