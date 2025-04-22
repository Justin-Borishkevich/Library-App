package com.example.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Books extends Fragment {
    private static final String ARG_PARAM_BOOKS = "ARG_PARAM_BOOKS";
    private static final String ARG_PARAM_GENRE = "ARG_PARAM_GENRE";

    private ArrayList<Book> books = new ArrayList<>();
    private String chosenGenre;

    public Books() {
        // Required empty public constructor
    }

    public static Books newInstance(ArrayList<Book> books, String genre) {
        Books fragment = new Books();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_BOOKS, books);
        args.putString(ARG_PARAM_GENRE, genre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            books = (ArrayList<Book>) getArguments().getSerializable(ARG_PARAM_BOOKS);
            chosenGenre = getArguments().getString(ARG_PARAM_GENRE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);
    }

    BookAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = view.findViewById(R.id.listViewBooks);
        adapter = new BookAdapter(requireContext(), R.layout.books_from_genre, books);
        listView.setAdapter(adapter);

        if (!books.isEmpty()){
            TextView genre = view.findViewById(R.id.chosenGenre);
            genre.setText(chosenGenre);

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoBookDetails(books.get(position));
            }
        });

        view.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.backButton();
            }
        });

    }

    HomeListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (HomeListener) context;
    }

    public interface HomeListener {
        void backButton();
        void gotoBookDetails(Book book);
    }
}