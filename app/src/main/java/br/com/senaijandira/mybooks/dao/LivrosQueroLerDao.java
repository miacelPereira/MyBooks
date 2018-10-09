package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import br.com.senaijandira.mybooks.model.LivrosQueroLer;

@Dao
public interface LivrosQueroLerDao {

    @Insert
    void inserirQueroLer(LivrosQueroLer livrosQueroLer);

    @Delete
    void deletarQueroLer(LivrosQueroLer livrosQueroLer);

    @Query("SELECT * FROM livrosQueroLer")
    LivrosQueroLer[] selecionarTodosLivrosQueroLer();
}
