package edu.utep.cs.cs4330.frighttime.ui.movies;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    public MovieList() {

    }
    public MovieList(List<Movie> movies) {

    }

    private static List<Movie> items;
    static{ //Basic List of Watched Items
        Object[][] itemInformation = new Object[][] {
                {"Alien",1979},
                {"Aliens",1986},
                {"The Babadook", 2014},
                {"Child's Play",1998},
                {"The Conjuring", 2013},
                {"Friday The 13th", 1980},
                {"Get Out",2017},
                {"Insidious", 2010},
                {"It",2017},
                {"A Nightmare on Elm Street", 1984},
                {"Psycho",1960},
                {"Psycho",1998},
                {"Saw", 2004},
                {"The Shining", 1980},
                {"The Texas Chain Saw Massacre", 1974},
                {"Us",2019},
                {"World War Z", 2013},
                {"Zombieland", 2009}};

        items = new ArrayList<>(itemInformation.length);
        for(Object[] mov: itemInformation) {
            items.add(new Movie((String) mov[0], (int) mov[1]));
        }
    }

    public static List<Movie> names() {
        List<Movie> movies = new ArrayList<>(items.size());
        for(Movie movie: items) {
            movies.add(movie);
        }
        return movies;
    }

    public static Movie find(String name) {
        for (Movie movie: items) {
            if (movie.getName().equalsIgnoreCase(name)) {
                return movie;
            }
        }
        return null;
    }

    public static void sortByTitle (List<Movie> movieList) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < movieList.size()-1; i++) {
                if(movieList.get(i).getName().compareToIgnoreCase(movieList.get(i+1).getName()) > 0){
                    Movie tempMovie = movieList.get(i);
                    movieList.set(i,movieList.get(i+1));
                    movieList.set(i+1,tempMovie);
                    sorted = false;
                }
            }
        }
    }
}
