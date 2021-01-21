package edu.utep.cs.cs4330.frighttime.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.utep.cs.cs4330.frighttime.R;
import edu.utep.cs.cs4330.frighttime.ui.user.User;
import edu.utep.cs.cs4330.frighttime.ui.user.UserFragment;

public class MovieFragment extends Fragment {
    MovieListAdapter movieAdapter;
    List<Movie> movies;
    EditText searchView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LinearLayout root = (LinearLayout)inflater.inflate(R.layout.fragment_movies, container, false);
        ListView list = (ListView)root.findViewById(R.id.movieListView);
        searchView = root.findViewById(R.id.editTextSearch);
        movies = MovieList.names();
        MovieList.sortByTitle(movies);
        movieAdapter = new MovieListAdapter(getActivity(),R.layout.movie_list_item,movies);
        list.setAdapter(movieAdapter);
        registerForContextMenu(list);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i , int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int middle, int end) {


            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return root;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.movie_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.add_to_favorites:
                User.getUser().addFavoriteMovie(movieAdapter.getItem(info.position));
                MovieList.sortByTitle(User.getUser().getFavoriteMovies());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}