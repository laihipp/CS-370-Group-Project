/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author amberlaihipp
 */
public class Utility {
    public Utility() {
               
        // create movies with times
        
        // create list of times for Up
        ArrayList<String> upTimes = new ArrayList<String>();
        upTimes.add("10:00 AM");
        upTimes.add("11:45 AM");
        upTimes.add("1:00 PM");
        upTimes.add("2:45 PM");
        upTimes.add("4:00 PM");
        
        // create movie 'Up'
        Movie up = new Movie("Up", upTimes);
        
        // add Up to list of Movies
        moviesList.add(up);
        
        // Create list of times for Soul
        ArrayList<String> soulTimes = new ArrayList<String>();
        soulTimes.add("10:30 AM");
        soulTimes.add("12:00 PM");
        soulTimes.add("1:30 PM");
        soulTimes.add("3:00 PM");
        soulTimes.add("4:30 PM");
        
        // create movie 'Soul'
        Movie soul = new Movie("Soul", soulTimes);
        
        // add Soul to list of Movies
        moviesList.add(soul);
    }
    
    public ArrayList<String> getRequestedTimes (String movieName) {
        // search through list of movies
        for (Movie m : moviesList) {
            if (m.getName().contains(movieName)) // if requested movie is found
                return m.getTimes(); // return list of times
        }
        
        // if movie not found
        return null;
    }
    
    // print out list of movie titles - not sure we need this
    public String printMovieTitles() {
        String output = "";
        for (Movie m : moviesList) {
            output += (m.getName() + "\n");
        }
        
        return output;
    }
    
    private static ArrayList<Movie> moviesList = new ArrayList<Movie>();
}
