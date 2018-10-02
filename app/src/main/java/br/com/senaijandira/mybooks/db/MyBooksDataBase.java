package br.com.senaijandira.mybooks.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.senaijandira.mybooks.dao.LivroDao;
import br.com.senaijandira.mybooks.dao.LivrosLidosDao;
import br.com.senaijandira.mybooks.dao.LivrosQueroLerDao;
import br.com.senaijandira.mybooks.model.Livro;

@Database(entities = {Livro.class}, version = 1)
public abstract class MyBooksDataBase extends RoomDatabase {

    public abstract LivroDao daoLivro();
    public abstract LivrosQueroLerDao daoLivrosQueroLer();
    public abstract LivrosLidosDao daoLivrosLidos();

}
