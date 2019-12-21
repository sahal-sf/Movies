package net.sahal.movies.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewsList implements Serializable {

    private int id, page, total_pages, total_results;
    private ArrayList<Results> results = new ArrayList();

    public ReviewsList(int id, int page, int total_pages, int total_results, ArrayList<Results> results) {
        this.id = id;
        this.page = page;
        this.total_pages = total_pages;
        this.total_results = total_results;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public ArrayList<Results> getResults() {
        return results;
    }
}
