package br.com.senaijandira.mybooks;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.senaijandira.mybooks.adapter.LidosQueroAdapter;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class FragmentLivroLidos extends Fragment {

    public static Livro[] livros;

    private MyBooksDataBase myBooksDB;

    LidosQueroAdapter adapter;

    ListView lstViewLivrosQueroLer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_livro_lidos, container, false);

        myBooksDB = Room.databaseBuilder(getActivity(), MyBooksDataBase.class, Utils.DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        lstViewLivrosQueroLer = v.findViewById(R.id.lstViewLivrosQueroLer);

        adapter = new LidosQueroAdapter(getActivity(), myBooksDB);

        lstViewLivrosQueroLer.setAdapter(adapter);

        livros = myBooksDB.daoLivro().selecionarTodos();

        adapter.addAll(livros);

        return v;

    }
}
