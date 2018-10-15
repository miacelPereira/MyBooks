package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import br.com.senaijandira.mybooks.model.LivrosLidos;

@Dao
public interface LivrosLidosDao {

    @Insert
    void inserirLivrosLidos(LivrosLidos livrosLidos);

    @Query("delete from livrosLidos where idGeral = :idGeral")
    void deletarLivroLidos(int idGeral);

    @Query("select livrosLidos.* from livrosLidos where idGeral = :id ")
    int verificaridGeral(int id);

}
