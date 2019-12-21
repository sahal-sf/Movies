package net.sahal.movies.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "SpokenLanguages")
public class SpokenLanguages implements Serializable {

    @PrimaryKey
    @NonNull
    private String iso_639_1;
    private String name;

    public SpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getName() {
        return name;
    }
}
