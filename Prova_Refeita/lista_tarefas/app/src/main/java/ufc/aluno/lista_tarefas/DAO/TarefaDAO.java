package ufc.aluno.lista_tarefas.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import ufc.aluno.lista_tarefas.Model.Tarefa;

import java.util.List;

@Dao
public interface TarefaDAO {

    @Query("SELECT * FROM Tarefa")
    List<Tarefa> getAll();

    @Query("SELECT * FROM Tarefa WHERE uid IN (:userIds)")
    List<Tarefa> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Tarefa WHERE titulo LIKE :titulo LIMIT 1")
    Tarefa findByName(String titulo);

    @Insert
    void insertAll(Tarefa... tarefas);

    @Delete
    void delete(Tarefa tarefa);
}
