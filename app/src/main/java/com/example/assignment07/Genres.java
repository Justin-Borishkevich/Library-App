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

import java.util.ArrayList;

public class Genres extends Fragment {
    private static final String ARG_PARAM_GENRES = "ARG_PARAM_GENRES";
    private ArrayList<String> genres = new ArrayList<>();

    public Genres() {
        // Required empty public constructor
    }

    public static Genres newInstance(ArrayList<String> genres) {
        Genres fragment = new Genres();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM_GENRES, genres);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            genres = getArguments().getStringArrayList(ARG_PARAM_GENRES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genres, container, false);
    }

    ArrayAdapter<String> adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, genres);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.gotoBooks(genres.get(position));
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
        void gotoBooks(String genre);
    }
}