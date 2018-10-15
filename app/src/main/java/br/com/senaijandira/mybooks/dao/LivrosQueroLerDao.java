package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import br.com.senaijandira.mybooks.model.LivrosQueroLer;

@Dao
public interface LivrosQueroLerDao {

    @Insert
    void inserirQueroLer(LivrosQueroLer livrosQueroLer);

    @Query("delete from livrosQueroLer where idGeral = :idGeral")
    void deletarQueroLer(int idGeral);

    @Query("SELECT * FROM livrosQueroLer")
    LivrosQueroLer[] selecionarTodosLivrosQueroLer();
}
