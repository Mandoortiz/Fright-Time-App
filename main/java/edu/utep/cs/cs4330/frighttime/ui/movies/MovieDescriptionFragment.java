package edu.utep.cs.cs4330.frighttime.ui.movies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import edu.utep.cs.cs4330.frighttime.R;


public class MovieDescriptionFragment extends Fragment {
    private View root;
    private TextView titleView;
    private ImageView imageView;
    private TextView directorView;
    private TextView plotView;
    Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_movie_description, container, false);

        return root;
    }

    public void setMovie(Movie movie) {
        View view = getView();
        titleView = view.findViewById(R.id.movieTitle);
        imageView = view.findViewById(R.id.moviePoster);
        plotView = view.findViewById(R.id.plot);
        directorView = view.findViewById(R.id.director);
        toast = Toast.makeText(getContext(), "Error Loading Movie Data", Toast.LENGTH_LONG);
        titleView.setText(movie.getName()+" ("+movie.getYear()+")");
        getMovieInfo(movie);
        displayToast();
    }

    private void getMovieInfo(Movie movie) {
        String movieName = movie.getName().replaceAll(" ", "+");
        String year = Integer.toString(movie.getYear());
        new MovieInfoParser(movieName,year).execute();
    }

    private void displayToast() {
    }


    private class MovieInfoParser extends AsyncTask<Void,Void,Void> {
        private String name;
        private String year;
        private final String KEY = "&apikey=ec85f2e9";
        private String description;
        private String poster;
        private String director;
        private String response;
        private Bitmap bitmap;
        private String apiResponse;
        private int i;

        private MovieInfoParser(String name, String year) {
            this.name = name;
            this.year = year;

        }
        @Override
        protected void onPreExecute() {
            response = "";
        }
        @Override
        protected Void doInBackground(Void... urls) {
            try {
                URL url = new URL("https://www.omdbapi.com/?t=" + name + "&y=" + year + KEY);
                URLConnection conn = url.openConnection();
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                if (HttpURLConnection.HTTP_OK == httpConn.getResponseCode()) {
                    InputStream in = httpConn.getInputStream();
                    while ((i = in.read()) != -1) {
                        response += (char) i;
                    }
                    JSONObject obj = new JSONObject(response);
                    description = obj.getString("Plot");
                    poster = obj.getString("Poster");
                    director = obj.getString("Director");
                    apiResponse = obj.getString("Response");
                    bitmap = loadImage(poster);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException io) {
                io.printStackTrace();
            } catch (JSONException je) {
                je.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (apiResponse == null) {
                toast = Toast.makeText(getContext(), "Error Retrieving Film Data", Toast.LENGTH_LONG);
                toast.show();
            }
            plotView.setText(description);
            directorView.setText(director);
            imageView.setImageBitmap(bitmap);

        }

        protected Bitmap loadImage(String url) {
            Bitmap bitmap = null;
            try {
                URL imageURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) imageURL
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}