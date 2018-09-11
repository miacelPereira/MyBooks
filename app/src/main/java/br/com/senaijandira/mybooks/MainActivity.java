package br.com.senaijandira.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.senaijandira.mybooks.model.Livro;

public class MainActivity extends AppCompatActivity {

    LinearLayout listaLivros;
    public static Livro[] livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLivros = findViewById(R.id.listaLivros);

        //Criando cadastros fakes
        livros = new Livro[]{
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.pequeno_principe), "O pequeno principe", getString(R.string.pequeno_principe)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.cinquenta_tons_cinza), "O pequeno principe", getString(R.string.cinquenta_tons)),
                new Livro(1, Utils.toByteArray(getResources(),R.drawable.kotlin_android), "O pequeno principe", getString(R.string.kotlin_android)),
        };
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        listaLivros.removeAllViews();
        for(Livro l : livros){
            criarLivro(l, listaLivros);
        }
    }

    public void criarLivro(Livro livro, ViewGroup root){

        View v = LayoutInflater.from(this).inflate(R.layout.livro_layout, root, false);

        /* Nesse caso deve procurar os id's dentro do view, que nesse caso é a "v" */
        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);

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
