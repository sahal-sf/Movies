package net.sahal.movies;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import net.sahal.movies.Models.*;

import java.util.*;

@Dao
public interface FavMoviesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavMovies(Results... favMovies);


    @Query("DELETE FROM favourite_List WHERE Id = :ID")
    void deleteFavMovies(String ID);


    @Query("SELECT * FROM favourite_List WHERE Id = :ID")
    Results getFavMovies(String ID);


    @Query("SELECT * FROM favourite_List")
    LiveData<List<Results>> getAllFavMovies();
}