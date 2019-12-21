package net.sahal.movies.Models;

import java.util.ArrayList;

public class MoviesList {

    private String page, total_results, total_pages;
    private ArrayList<Results> results;

    public MoviesList(String page, String total_results, String total_pages, ArrayList<Results> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Results> getResults() {
        return results;
    }
}

