package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import br.com.senaijandira.mybooks.model.LivrosLidos;

@Dao
public interface LivrosLidosDao {

    @Insert
    void inserirLivrosLidos(LivrosLidos livrosLidos);

    @Delete
    void deletarLivroLidos(LivrosLidos livrosLidos);

    @Query("SELECT * FROM livrosLidos")
    LivrosLidos[] selecionarTodosLivrosLidos();
}
