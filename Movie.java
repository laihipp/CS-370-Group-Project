package main;

import java.util.ArrayList;

/**
 *
 * @author amberlaihipp
 */
public class Movie {
    public Movie(String title, ArrayList<String> times) {
        movieTitle = title;
        movieTimes = new ArrayList<>(times);
    }
    
    public String getName() {
        return movieTitle;
    }
    
    public ArrayList<String> getTimes() {
        return movieTimes;
    }
    
    public void setTimes(String time) {
        // might not need this
    }
    
    
    private String movieTitle;
    private ArrayList<String> movieTimes;
}
