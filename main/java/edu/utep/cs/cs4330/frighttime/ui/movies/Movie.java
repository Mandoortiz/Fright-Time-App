package edu.utep.cs.cs4330.frighttime.ui.movies;

import android.widget.ImageView;

import java.util.List;

public class Movie {
    private String name;
    private int year;
    private String posterUrl;
    private String plot;
    private List<Scare> scares;

    public Movie() {

    }
    public Movie(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public void setPosterUrl(String posterUrl){
        this.posterUrl = posterUrl;
    }

    public void setPlot(String plot) {
        this.plot = plot;

    }
    public void setScares(List<Scare> scares) {
        this.scares=scares;
    }

    public String getName(){
        return name;
    }
    public int getYear() {
        return year;
    }
    public String getPosterUrl() {
        return posterUrl;
    }

    public String getPlot() {
        return plot;
    }
}
