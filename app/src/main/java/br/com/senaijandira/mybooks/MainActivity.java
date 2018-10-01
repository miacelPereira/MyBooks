package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import br.com.senaijandira.mybooks.adapter.LivroAdapter;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class MainActivity extends AppCompatActivity {

    public static Livro[] livros;
    private MyBooksDataBase myBooksDB;

    //Adapter para criar a lista de livros
    LivroAdapter adapter;

    //ListView do xml que vai receber o adapter
    ListView lstViewLivros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciando Banco de dados
        myBooksDB = Room.databaseBuilder(getApplicationContext(), MyBooksDataBase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        lstViewLivros = findViewById(R.id.lstViewLivros);

        //Criar adapter
        adapter = new LivroAdapter(this, myBooksDB);

        /* Colocando o adapter na ListView */
        lstViewLivros.setAdapter(adapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        //SELECT NO BANCO DE DADOS
        livros = myBooksDB.daoLivro().selecionarTodos();

        /* Limpando adapter para não duplicar livros */
        adapter.clear();

        /* Adicionando novamente o adapter */
        adapter.addAll(livros);
    }

    public void abrirCadastro(View v){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}
