package com.example.assignment07;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Genres.HomeListener, Books.HomeListener, BookDetails.HomeListener{
    ArrayList<String> genres = Data.getAllGenres();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, Genres.newInstance(genres), "GenresFragment")
                .commit();

    }

    @Override
    public void gotoBooks(String genreForBooks) {
        if (genreForBooks != null) {
            ArrayList<Book> books = Data.getBooksByGenre(genreForBooks);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main, Books.newInstance(books, genreForBooks))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void gotoBookDetails(Book book) {
        if (book != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main, BookDetails.newInstance(book))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void backButton() {
        getSupportFragmentManager().popBackStack();
    }


}