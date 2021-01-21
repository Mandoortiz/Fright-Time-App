package edu.utep.cs.cs4330.frighttime.ui.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.utep.cs.cs4330.frighttime.R;

public class MovieListAdapter extends ArrayAdapter<Movie>{
    private Movie current;
    private Context context;
    private TextView nameView;
    private TextView yearView;

    public MovieListAdapter(Context context, int resourceId, List<Movie> items) {
        super(context, resourceId, items);
        this.context =context;
    }

    public interface ItemClickListener {
        void itemClicked(Movie item);
    }

    private ItemClickListener listener;

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_item, parent, false);

            LinearLayout layout = convertView.findViewById(R.id.movieListView);
            layout.setOnClickListener(view -> {
                Intent intent = new Intent(context,MovieActivity.class);
                Movie movie = getItem(position);
                intent.putExtra("title", movie.getName());
                intent.putExtra("year",movie.getYear());
                context.startActivity(intent);
            });
        }

        current = getItem(position);
        nameView = convertView.findViewById(R.id.listMovieName);
        yearView = convertView.findViewById(R.id.listMovieYear);
        nameView.setText(current.getName());
        yearView.setText(Integer.toString(current.getYear()));
        nameView.setTag(current);
        return convertView;
    }
}
