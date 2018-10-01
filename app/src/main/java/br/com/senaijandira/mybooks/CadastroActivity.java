package br.com.senaijandira.mybooks;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class CadastroActivity extends AppCompatActivity {

    Bitmap livroCapa; // para converter a imagem em binário
    ImageView imgLivroCapa;
    EditText txtTitulo, txtDescricao;

    private final int COD_REQ_GALERIA = 101; // VÁRIAVEL IMUTAVEL

    private MyBooksDataBase myBooksDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        imgLivroCapa = findViewById(R.id.imgLivroCapa);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtTitulo = findViewById(R.id.txtTitulo);

        //Instancia do BANDO DE DADOS
        myBooksDB = Room.databaseBuilder(getApplicationContext(),MyBooksDataBase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public void abrirGaleria(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("image/*"); // FILTRANDO O INTENT SOMENTE PARA IMAGEM
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_REQ_GALERIA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == COD_REQ_GALERIA && resultCode == Activity.RESULT_OK){
            try{
                InputStream  input = getContentResolver().openInputStream(data.getData()); // PEGANDO O ARQUIVO SELECIONADO
                livroCapa = BitmapFactory.decodeStream(input); // CONVERTENDO PARA BITMAP
                imgLivroCapa.setImageBitmap(livroCapa); // EXIBINDO NA TELA A IMAGEM QUE O USUÁRIO SELECIONOU


            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void salvarLivro(View view) {
        byte[] capa = new byte[0];

        if(livroCapa != null) {
            capa = Utils.toByteArray(livroCapa);
        }

        String titulo = txtTitulo.getText().toString();
        String descricao = txtDescricao.getText().toString();
        Livro livro = new Livro(capa, titulo, descricao);

        if(!titulo.equals("") && !descricao.equals("") && livroCapa!=null){

           /* Passando os parametros para o banco de dados */
           myBooksDB.daoLivro().inserir(livro);

            alert("Sucesso!", "Livro salvo com sucesso!", true);
        }
        else{
            alert("Erro!", "Preencha todos os campos!", false);
        }
    }

    public void alert(String titulo, String mensagem, boolean sucesso){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        if(sucesso){
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        builder.create();
        builder.show();


    }
}
