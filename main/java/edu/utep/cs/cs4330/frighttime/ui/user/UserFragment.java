package edu.utep.cs.cs4330.frighttime.ui.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.utep.cs.cs4330.frighttime.R;
import edu.utep.cs.cs4330.frighttime.ui.movies.Movie;
import edu.utep.cs.cs4330.frighttime.ui.movies.MovieFragment;
import edu.utep.cs.cs4330.frighttime.ui.movies.MovieList;
import edu.utep.cs.cs4330.frighttime.ui.movies.MovieListAdapter;

public class UserFragment extends Fragment{
    TextView usernameView;
    ImageView profilePicture;
    ListView list;
    MovieListAdapter movieAdapter;
    User user;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        usernameView = root.findViewById(R.id.username_view);
        profilePicture = root.findViewById(R.id.profile_picture);
        user = User.getUser();
        usernameView.setText(user.getUsername());
        if(list == null) {
            list = (ListView)root.findViewById(R.id.favorite_movies);
            movieAdapter = new MovieListAdapter(getActivity(),R.layout.movie_list_item, user.getFavoriteMovies());
            list.setAdapter(movieAdapter);
        }
        registerForContextMenu(list);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.user_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.delete:
                String name = movieAdapter.getItem(info.position).getName();
                movieAdapter.remove(movieAdapter.getItem(info.position));
                User.removeMovie(name);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}