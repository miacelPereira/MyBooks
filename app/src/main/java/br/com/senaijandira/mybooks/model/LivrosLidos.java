package br.com.senaijandira.mybooks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "livrosLidos")
public class LivrosLidos {

    @PrimaryKey(autoGenerate = true)
    private int idLidos;

    @ForeignKey(entity = Livro.class , childColumns = "idGeral", parentColumns = "id")
    private int idGeral;

    public void setIdLidos(int idLidos) {
        this.idLidos = idLidos;
    }

    public int getIdLidos() {
        return idLidos;
    }

    public LivrosLidos(){}
    public LivrosLidos(int idGeral){}
}
