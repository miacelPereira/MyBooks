package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class MainActivity extends AppCompatActivity {

    LinearLayout listaLivros;
    public static Livro[] livros;
    private MyBooksDataBase myBooksDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciando Banco de dados
        myBooksDB = Room.databaseBuilder(getApplicationContext(), MyBooksDataBase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        listaLivros = findViewById(R.id.listaLivros);
        /*Criando cadastros fakes
        livros = new Livro[]{
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.pequeno_principe), "O pequeno principe", getString(R.string.pequeno_principe)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza), "O pequeno principe", getString(R.string.cinquenta_tons)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.kotlin_android), "O pequeno principe", getString(R.string.kotlin_android)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.pequeno_principe), "O pequeno principe", getString(R.string.pequeno_principe)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza), "O pequeno principe", getString(R.string.cinquenta_tons)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.kotlin_android), "O pequeno principe", getString(R.string.kotlin_android)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.pequeno_principe), "O pequeno principe", getString(R.string.pequeno_principe)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza), "O pequeno principe", getString(R.string.cinquenta_tons)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.kotlin_android), "O pequeno principe", getString(R.string.kotlin_android)),
        };*/
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        //SELECT NO BANCO DE DADOS
        livros = myBooksDB.daoLivro().selecionarTodos();

        listaLivros.removeAllViews();
        for(Livro l : livros){
            criarLivro(l, listaLivros);
        }
    }

    public void deletarLivro(final Livro livro, final View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Deletar");
        alert.setMessage("Tem certeza que deseja deletar?");
        alert.setNegativeButton("Não", null);
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Deletar o livro do banco
                myBooksDB.daoLivro().deletar(livro);
                //Deletar livro da tela
                listaLivros.removeView(v);
            }
        });
        alert.show();
    }

    public void criarLivro(final Livro livro, ViewGroup root){

        final View v = LayoutInflater.from(this).inflate(R.layout.livro_layout, root, false);

        /* Nesse caso deve procurar os id's dentro do view, que nesse caso é a "v" */
        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);
        ImageView imgDeleteLivro = v.findViewById(R.id.imgDeleteLivro);

        imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarLivro(livro , v);
            }
        });

        //Setando os valores
        imgLivroCapa.setImageBitmap(Utils.toBitmap(livro.getCapa()));
        txtLivroTitulo.setText(livro.getTitulo());
        txtLivroDescricao.setText(livro.getDescricao());

        // Exibindo na tela
        root.addView(v);

    }

    public void abrirCadastro(View v){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
