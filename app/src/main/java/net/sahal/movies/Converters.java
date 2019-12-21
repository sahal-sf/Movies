package net.sahal.movies;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sahal.movies.Models.BelongsToCollection;
import net.sahal.movies.Models.Ggenres;
import net.sahal.movies.Models.ProductionCompanies;
import net.sahal.movies.Models.ProductionCountries;
import net.sahal.movies.Models.Results;
import net.sahal.movies.Models.SpokenLanguages;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static ArrayList<Ggenres> fromGeneres(String value) {
        Type listType = new TypeToken<ArrayList<Ggenres>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListGeneres(ArrayList<Ggenres> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<ProductionCompanies> fromProductionCompanies(String value) {
        Type listType = new TypeToken<ArrayList<ProductionCompanies>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListProductionCompanies(ArrayList<ProductionCompanies> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<ProductionCountries> fromProductionCountries(String value) {
        Type listType = new TypeToken<ArrayList<ProductionCountries>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListProductionCountries(ArrayList<ProductionCountries> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<SpokenLanguages> fromSpokenLanguages(String value) {
        Type listType = new TypeToken<ArrayList<SpokenLanguages>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListSpokenLanguages(ArrayList<SpokenLanguages> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static BelongsToCollection fromBelongsToCollection(String value) {
        Type listType = new TypeToken<BelongsToCollection>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListBelongsToCollection(BelongsToCollection list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<Integer> fromgenreIds(String value) {
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListgenreIds(ArrayList<Integer> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<Results> fromResults(String value) {
        Type listType = new TypeToken<ArrayList<Results>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListResults(ArrayList<Results> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
