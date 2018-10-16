package br.com.senaijandira.mybooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.EditarLivro;
import br.com.senaijandira.mybooks.model.Livro;
import br.com.senaijandira.mybooks.model.LivrosLidos;
import br.com.senaijandira.mybooks.model.LivrosQueroLer;

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
            v = LayoutInflater.from(getContext()).inflate(R.layout.livro_layout,parent,false);
        }
        final Livro  livro = getItem(position);

        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);
        ImageView imgDeleteLivro = v.findViewById(R.id.imgDeleteLivro);
        ImageView imgEditarLivro = v.findViewById(R.id.imgEditarLivro);
        Button btnJaLi = v.findViewById(R.id.btnJaLi);
        Button btnVouLer = v.findViewById(R.id.btnVouLer);

       imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!livro.getLista()) {
                    deletarLivro(livro);
                    Toast toast = Toast.makeText(getContext(), "Livro excluido", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getContext(), "Este livro está em uma lista", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        imgEditarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditarLivro.class);
                intent.putExtra("livro", livro.getId());
                getContext().startActivity(intent);
            }
        });
        btnJaLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!livro.getLista()) {
                    myBooksDB.daoLivro().updateLista(true, livro.getId());
                    livro.setLista(true);
                    LivrosLidos livroLido = new LivrosLidos();
                    livroLido.setIdGeral(livro.getId());
                    myBooksDB.daoLivrosLidos().inserirLivrosLidos(livroLido);
                    Toast toast = Toast.makeText(getContext(), "Adicionado em Livros lidos", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getContext(), "Este livro já está em uma lista", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        btnVouLer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!livro.getLista()) {
                    myBooksDB.daoLivro().updateLista(true, livro.getId());
                    livro.setLista(true);
                    LivrosQueroLer ler = new LivrosQueroLer();
                    ler.setIdGeral(livro.getId());
                    myBooksDB.daoLivrosQueroLer().inserirQueroLer(ler);
                    Toast toast = Toast.makeText(getContext(), "Adicionado em Quero ler", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                        Toast toast = Toast.makeText(getContext(), "Este livro já está em uma lista", Toast.LENGTH_SHORT);
                        toast.show();
                }
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

}
