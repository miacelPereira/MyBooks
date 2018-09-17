package br.com.senaijandira.mybooks;

import android.app.Activity;
import android.app.AlertDialog;
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
import java.util.Arrays;

import br.com.senaijandira.mybooks.model.Livro;

public class CadastroActivity extends AppCompatActivity {

    Bitmap livroCapa; // para converter a imagem em binário
    ImageView imgLivroCapa;
    EditText txtTitulo, txtDescricao;

    private final int COD_REQ_GALERIA = 101; // VÁRIAVEL IMUTAVEL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        imgLivroCapa = findViewById(R.id.imgLivroCapa);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtTitulo = findViewById(R.id.txtTitulo);
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
        Livro livro = new Livro(0, capa, titulo, descricao);
        int tamanhoArray = MainActivity.livros.length;

        if(!titulo.equals("") && !descricao.equals("") && livroCapa!=null){
            MainActivity.livros = Arrays.copyOf(MainActivity.livros, tamanhoArray + 1);
            MainActivity.livros[tamanhoArray] = livro;
            alert("Sucesso!", "Livro salvo com sucesso!");
        }
        else{
            alert("Erro!", "Preencha todos os campos!");
        }
    }

    public void alert(String titulo, String mensagem){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        builder.create();
        builder.setCancelable(false);
        builder.show();


    }
}
