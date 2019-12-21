package net.sahal.movies;

import net.sahal.movies.Models.*;

import retrofit2.*;
import retrofit2.http.*;

interface Api {

    String Base_URL = "https://api.themoviedb.org/3/movie/";

    @GET("{ID}")
    Call<MoviesList> getMovies(
            @Path("ID") String name,
            @Query("api_key") String key
    );

    @GET("{ID}")
    Call<Results> getDetails(
            @Path("ID") String name,
            @Query("api_key") String key
    );

    @GET("{ID}/videos")
    Call<TrailersList> getVideos(
            @Path("ID") String name,
            @Query("api_key") String key
    );

    @GET("{ID}/reviews")
    Call<ReviewsList> getReviews(
            @Path("ID") String name,
            @Query("api_key") String key
    );
}