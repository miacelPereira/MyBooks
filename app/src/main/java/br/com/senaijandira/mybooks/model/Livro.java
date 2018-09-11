package br.com.senaijandira.mybooks.model;

public class Livro {
    private int id;

    //PARA SALVAR A IMAGEM DA CAPA DO LIVRO
    private byte[] capa;

    private String descricao;
    private String titulo;


    //Construtores
    public Livro(){

    }
    public Livro(int id, byte[] capa, String titulo, String descricao){
        this.id=id;
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
