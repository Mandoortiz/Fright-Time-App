package edu.utep.cs.cs4330.frighttime.ui.user;

import java.util.ArrayList;
import java.util.List;

import edu.utep.cs.cs4330.frighttime.ui.movies.Movie;

public class User {
    private static User user;
    private static String username;
    private static List<Movie> favoriteMovies;

    private User () {
        this.username = "Mandoortiz";
        this.favoriteMovies = new ArrayList<Movie>();
    }


    public static User getUser() {
        if(user == null) {
            user = new User();
        }
        return user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavoriteMovies(List<Movie> favoriteMovies) {

    }

    public String getUsername() {
        return username;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public static void addFavoriteMovie(Movie movie) {
        favoriteMovies.add(movie);
    }

    public static void removeMovie(String name) {
        //PriceFinderItem itemToRemove;
        for (Movie movie: favoriteMovies) {
            if (movie.getName().equalsIgnoreCase(name)) {
                favoriteMovies.remove(movie);
                break;
            }
        }
    }
}
