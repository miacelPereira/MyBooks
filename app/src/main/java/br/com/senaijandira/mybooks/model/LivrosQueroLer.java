package br.com.senaijandira.mybooks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "livrosQueroLer")
public class LivrosQueroLer {

    @PrimaryKey (autoGenerate = true)
    private int idLivrosQueroLer;

    @ForeignKey(entity = Livro.class, childColumns = "idGeral", parentColumns = "id")
    private int idGeral;

    public void setIdLivrosQueroLer(int idLivrosQueroLer) {
        this.idLivrosQueroLer = idLivrosQueroLer;
    }

    public int getIdLivrosQueroLer() {
        return idLivrosQueroLer;
    }

    public LivrosQueroLer(){}
    public LivrosQueroLer(int idGeral){}
}
