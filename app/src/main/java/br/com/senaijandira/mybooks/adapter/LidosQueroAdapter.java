package br.com.senaijandira.mybooks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class LidosQueroAdapter extends ArrayAdapter<Livro> {

    public static Livro[] livros;
    private MyBooksDataBase myBooksDB;

    public LidosQueroAdapter (Context ctx, MyBooksDataBase myBooksDB){
        super(ctx, 0 , new ArrayList<Livro>());
        this.myBooksDB = myBooksDB;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /* Inflando o XML correto no Adapter */
        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.livros_lidos_quero,parent,false);
        }

        final Livro livro = getItem(position);

        ImageView imgLivroQueroLidos = v.findViewById(R.id.imgLivroQueroLidos);
        ImageView imgDeleteLivroLidos = v.findViewById(R.id.imgDeleteLivroLidos);
        TextView txtQueroLidoTitulo = v.findViewById(R.id.txtQueroLidoTitulo);
        TextView txtQueroLidoDescricao = v.findViewById(R.id.txtQueroLidoDescricao);

        imgLivroQueroLidos.setImageBitmap(Utils.toBitmap(livro.getCapa()));
        txtQueroLidoTitulo.setText(livro.getTitulo());
        txtQueroLidoDescricao.setText(livro.getDescricao());

        imgDeleteLivroLidos.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Seguinte, se a pessoa clicou na nessa lixeira ela está em ou em livro lidos ou em quero ler, nesse caso o programa vai tentar excluir de uma tabela, se não conseguir tenta da outra
                 if(myBooksDB.daoLivrosLidos().verificaridGeral(livro.getId()) != 0 ) {
                    myBooksDB.daoLivrosLidos().deletarLivroLidos(livro.getId());
                    myBooksDB.daoLivro().updateLista(false, livro.getId());
                    remove(livro);
                    Toast toast = Toast.makeText(getContext(), "Deletado de livro lidos", Toast.LENGTH_SHORT);
                    toast.show();

                 }else{
                     myBooksDB.daoLivrosQueroLer().deletarQueroLer(livro.getId());
                     myBooksDB.daoLivro().updateLista(false, livro.getId());
                     remove(livro);
                     Toast toast = Toast.makeText(getContext(), "Deletado de Quero ler", Toast.LENGTH_SHORT);
                     toast.show();
                }

             }
         });
        return v;
    }
}
