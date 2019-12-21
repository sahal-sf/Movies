package net.sahal.movies.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ProductionCountries")
public class ProductionCountries implements Serializable {

    @PrimaryKey
    @NonNull
    private String iso_3166_1;
    private String name;

    public ProductionCountries(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getName() {
        return name;
    }
}
