package net.sahal.movies.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.*;
import androidx.room.*;

import java.io.Serializable;
import java.util.ArrayList;


@Entity(tableName = "favourite_List")
public class Results implements Parcelable, Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    private String poster_path, overview, release_date, original_title, original_language, title,
            backdrop_path, homepage, imdb_id, status, tagline, key, name, site, type, iso_639_1,
            iso_3166_1, author, content, url, adult, video, popularity, vote_count, vote_average,
            budget, revenue, runtime, size;
    private ArrayList<Ggenres> genres;
    private ArrayList<ProductionCompanies> production_companies;
    private ArrayList<ProductionCountries> production_countries;
    private ArrayList<SpokenLanguages> spoken_languages;
    private BelongsToCollection belongs_to_collection;
    private ArrayList<Integer> genre_ids;
    private ArrayList<Results> trailerList;
    private ArrayList<Results> reviewList;

    public @Ignore
    Results(@NonNull String id, String poster_path, String overview, String release_date, String original_title, String original_language, String title, String backdrop_path, String adult, String video, String popularity, String vote_count, String vote_average, ArrayList<Integer> genre_ids) {
        this.id = id;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
        this.original_language = original_language;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.video = video;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.genre_ids = genre_ids;
    }

    public Results(@NonNull String id, String poster_path, String overview, String release_date, String original_title, String original_language, String title, String backdrop_path, String homepage, String imdb_id, String status, String tagline, String adult, String video, String popularity, String vote_count, String vote_average, String budget, String revenue, String runtime, ArrayList<Ggenres> genres, ArrayList<ProductionCompanies> production_companies, ArrayList<ProductionCountries> production_countries, ArrayList<SpokenLanguages> spoken_languages, BelongsToCollection belongs_to_collection) {
        this.id = id;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
        this.original_language = original_language;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.homepage = homepage;
        this.imdb_id = imdb_id;
        this.status = status;
        this.tagline = tagline;
        this.adult = adult;
        this.video = video;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.budget = budget;
        this.revenue = revenue;
        this.runtime = runtime;
        this.genres = genres;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.spoken_languages = spoken_languages;
        this.belongs_to_collection = belongs_to_collection;
    }

    public @Ignore
    Results(@NonNull String id, String key, String name, String site, String type, String iso_639_1, String iso_3166_1, String size) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.type = type;
        this.iso_639_1 = iso_639_1;
        this.iso_3166_1 = iso_3166_1;
        this.size = size;
    }

    public @Ignore
    Results(@NonNull String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {

        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeString(original_title);
        parcel.writeString(original_language);
        parcel.writeString(title);
        parcel.writeString(backdrop_path);
        parcel.writeString(homepage);
        parcel.writeString(imdb_id);
        parcel.writeString(status);
        parcel.writeString(tagline);
        parcel.writeString(key);
        parcel.writeString(name);
        parcel.writeString(site);
        parcel.writeString(type);
        parcel.writeString(iso_639_1);
        parcel.writeString(iso_3166_1);
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(url);
        parcel.writeString(adult);
        parcel.writeString(video);
        parcel.writeString(popularity);
        parcel.writeString(vote_count);
        parcel.writeString(vote_average);
        parcel.writeString(budget);
        parcel.writeString(revenue);
        parcel.writeString(runtime);
        parcel.writeString(size);
        parcel.writeList(genres);
        parcel.writeList(production_companies);
        parcel.writeList(production_countries);
        parcel.writeList(spoken_languages);
        parcel.writeList(genre_ids);
        parcel.writeList(trailerList);
        parcel.writeList(reviewList);
        parcel.writeParcelable(belongs_to_collection, i);

    }

    private Results(Parcel in) {

        id = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        homepage = in.readString();
        imdb_id = in.readString();
        status = in.readString();
        tagline = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        type = in.readString();
        iso_639_1 = in.readString();
        iso_3166_1 = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
        adult = in.readString();
        video = in.readString();
        popularity = in.readString();
        vote_count = in.readString();
        vote_average = in.readString();
        budget = in.readString();
        revenue = in.readString();
        runtime = in.readString();
        size = in.readString();
        genres = in.readArrayList(null);
        production_companies = in.readArrayList(null);
        production_countries = in.readArrayList(null);
        spoken_languages = in.readArrayList(null);
        genre_ids = in.readArrayList(null);
        trailerList = in.readArrayList(Results.class.getClassLoader());
//         = in.readParcelable(null);
        reviewList = in.readArrayList(Results.class.getClassLoader());
        belongs_to_collection = in.readParcelable(BelongsToCollection.class.getClassLoader());

    }

    // Getter
    @NonNull
    public String getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getAdult() {
        return adult;
    }

    public String getVideo() {
        return video;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getBudget() {
        return budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getSize() {
        return size;
    }

    public ArrayList<Ggenres> getGenres() {
        return genres;
    }

    public ArrayList<ProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public ArrayList<ProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public ArrayList<SpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public BelongsToCollection getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public ArrayList<Results> getTrailerList() {
        return trailerList;
    }

    public ArrayList<Results> getReviewList() {
        return reviewList;
    }

    //Setter
    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setGenres(ArrayList<Ggenres> genres) {
        this.genres = genres;
    }

    public void setProduction_companies(ArrayList<ProductionCompanies> production_companies) {
        this.production_companies = production_companies;
    }

    public void setProduction_countries(ArrayList<ProductionCountries> production_countries) {
        this.production_countries = production_countries;
    }

    public void setSpoken_languages(ArrayList<SpokenLanguages> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public void setBelongs_to_collection(BelongsToCollection belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setTrailerList(ArrayList<Results> trailerList) {
        this.trailerList = trailerList;
    }

    public void setReviewList(ArrayList<Results> reviewList) {
        this.reviewList = reviewList;
    }
}
