package net.sahal.movies.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class TrailersList implements Serializable {

    private String id;
    private ArrayList<Results> results = new ArrayList();

    public TrailersList(String id, ArrayList<Results> results) {
        this.id = id;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Results> getResults() {
        return results;
    }
}


