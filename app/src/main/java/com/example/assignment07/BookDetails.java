package com.example.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment07.databinding.FragmentBookDetailsBinding;

public class BookDetails extends Fragment {
    private static final String ARG_PARAM_BOOK = "ARG_PARAM_BOOK";

    private Book book;

    public BookDetails() {
        // Required empty public constructor
    }

    public static BookDetails newInstance(Book book) {
        BookDetails fragment = new BookDetails();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_BOOK, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable(ARG_PARAM_BOOK);
        }
    }

    FragmentBookDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (book != null) {
            binding.textViewTitle.setText(book.getTitle());
            binding.textViewAuthor.setText(book.getAuthor());
            binding.textViewGenre.setText(book.getGenre());
            binding.textViewYear.setText(String.valueOf(book.getYear()));
        }

        binding.buttonBackDetails.setOnClickListener(new View.OnClickListener() {
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
    }
}