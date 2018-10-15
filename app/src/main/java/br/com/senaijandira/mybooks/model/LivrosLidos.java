package br.com.senaijandira.mybooks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;

@Entity (tableName = "livrosLidos")
public class LivrosLidos {

    @PrimaryKey(autoGenerate = true)
    private int idLidos;

    @ForeignKey(entity = Livro.class , childColumns = "idGeral", parentColumns = "id")
    private int idGeral;

    // GET e SET
    public void setIdLidos(int idLidos) {
        this.idLidos = idLidos;
    }

    public int getIdLidos() {
        return idLidos;
    }

    public int getIdGeral() {
        return idGeral;
    }

    public void setIdGeral(int idGeral) {
        this.idGeral = idGeral;
    }

    // CONSTRUTOR
    public LivrosLidos(){}
    public LivrosLidos(int idGeral){}
}
