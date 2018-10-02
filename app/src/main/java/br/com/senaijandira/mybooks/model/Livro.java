package br.com.senaijandira.mybooks.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "livros")
public class Livro {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //PARA SALVAR A IMAGEM DA CAPA DO LIVRO
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] capa;

    private String descricao;
    private String titulo;


    //Construtores
    public Livro(){}
    public Livro(byte[] capa, String titulo, String descricao){
        this.capa=capa;
        this.titulo=titulo;
        this.descricao=descricao;
    }

    // GET's e SET's
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
