package net.sahal.movies.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Ggenres")
public class Ggenres implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;

    public Ggenres(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
