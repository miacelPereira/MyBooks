package br.com.senaijandira.mybooks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.model.LivrosLidos;

public class LivroAdapter extends ArrayAdapter<Livro>{

    public static Livro[] livros;
    private MyBooksDataBase myBooksDB;
    private LivrosLidos livrosLidos;

    public LivroAdapter (Context ctx, MyBooksDataBase myBooksDB){
        super(ctx, 0 , new ArrayList<Livro>());
        this.myBooksDB = myBooksDB;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        /* Pegando o arquivo xml */
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_todos_livros,parent,false);
        }
        final Livro  livro = getItem(position);

        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);
        ImageView imgDeleteLivro = v.findViewById(R.id.imgDeleteLivro);
        Button btnJaLi = v.findViewById(R.id.btnJaLi);

       imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarLivro(livro);
            }
        });

        btnJaLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imgLivroCapa.setImageBitmap(Utils.toBitmap(livro.getCapa()));
        txtLivroTitulo.setText(livro.getTitulo());
        txtLivroDescricao.setText(livro.getDescricao());

        return v;
    }

    /* Remove o livro */
    private void deletarLivro(Livro livro){
        myBooksDB.daoLivro().deletar(livro);
        remove(livro);
    }

    /* Adicionando o livro na table LivrosLidos */
    private void adicionarLivroLidos(Livro livro, LivrosLidos livrosLidos){
        myBooksDB.daoLivrosLidos().inserirLivrosLidos(livrosLidos);
    }
}
