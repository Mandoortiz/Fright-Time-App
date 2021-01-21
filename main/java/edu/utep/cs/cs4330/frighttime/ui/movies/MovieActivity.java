package edu.utep.cs.cs4330.frighttime.ui.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import edu.utep.cs.cs4330.frighttime.R;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        String name = intent.getStringExtra("title");
        int year = intent.getIntExtra("year",0);
        Movie movie = new Movie(name,year);
        MovieDescriptionFragment fragment = (MovieDescriptionFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_movie_description);
        fragment.setMovie(movie);

    }


}