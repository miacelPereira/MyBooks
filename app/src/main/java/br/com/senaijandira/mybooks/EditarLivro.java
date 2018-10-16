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

public class EditarLivro extends AppCompatActivity{
    Bitmap livroCapa;
    ImageView imgLivroCapa;
    EditText txtTitulo, txtDescricao;

    int idLivro, status;

    private final int COD_REQ_GALERIA = 101;

    private MyBooksDataBase myBooksDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        imgLivroCapa = findViewById(R.id.imgLivroCapa);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtTitulo = findViewById(R.id.txtTitulo);

        myBooksDB = Room.databaseBuilder(getApplicationContext(),MyBooksDataBase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        idLivro = getIntent().getIntExtra("livro",0);

        Livro l = myBooksDB.daoLivro().selecionarLivro(idLivro);
        imgLivroCapa.setImageBitmap(Utils.toBitmap(l.getCapa()));
        livroCapa = BitmapFactory.decodeByteArray(l.getCapa(), 0, l.getCapa().length);
        txtDescricao.setText(l.getDescricao());
        txtTitulo.setText(l.getTitulo());
    }

    public void abrirGaleria(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_REQ_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == COD_REQ_GALERIA && resultCode == Activity.RESULT_OK){
            try{
                InputStream input = getContentResolver().openInputStream(data.getData());
                livroCapa = BitmapFactory.decodeStream(input);
                imgLivroCapa.setImageBitmap(livroCapa);

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
        Boolean faseLista = myBooksDB.daoLivro().faseLista(idLivro);
        String titulo = txtTitulo.getText().toString();
        String descricao = txtDescricao.getText().toString();

        Livro livro = new Livro(idLivro, capa, titulo, descricao,faseLista);

        if(!titulo.equals("") && !descricao.equals("") && livroCapa!=null){

            myBooksDB.daoLivro().atualizar(livro);

            alert("Sucesso!", "Livro alterado com sucesso!", true);
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
