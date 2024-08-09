package com.example.prova.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.prova.DAO.TarefaDAO;
import com.example.prova.Model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TarefaDAO tarefaDAO();
}
