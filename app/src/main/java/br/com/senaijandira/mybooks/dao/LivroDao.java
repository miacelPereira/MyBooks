package br.com.senaijandira.mybooks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import br.com.senaijandira.mybooks.model.Livro;

@Dao
public interface LivroDao {

    @Insert
    void inserir(Livro l);

    @Delete
    void deletar(Livro l);

    @Update
    void atualizar(Livro l);

    @Query("UPDATE livros SET lista = :lista WHERE id = :id")
    void updateLista(Boolean lista, int id);

    @Query("SELECT * FROM livros")
    Livro[] selecionarTodos();

    @Query("SELECT livros.* from livros, livrosLidos WHERE livros.id = livrosLidos.idGeral")
    Livro[] selecionarTodosLivrosLidos();

    @Query("SELECT livros.* from livros, livrosQueroLer WHERE livros.id = livrosQueroLer.idGeral")
    Livro[]selecionarTodosLivrosQuero();

    @Query("SELECT livros.* FROM livros WHERE livros.id = :id")
    Livro selecionarLivro(int id);
}
