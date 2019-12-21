package net.sahal.movies.Models;

import androidx.annotation.*;
import androidx.room.*;

import java.io.Serializable;

@Entity(tableName = "ProductionCompanies")
public class ProductionCompanies implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    private String logo_path, name, origin_country;

    public ProductionCompanies(@NonNull String id, String logo_path, String name, String origin_country) {
        this.id = id;
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getName() {
        return name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }
}
