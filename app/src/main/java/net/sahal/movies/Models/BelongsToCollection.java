package net.sahal.movies.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BelongsToCollection")
public class BelongsToCollection implements Parcelable {

    @PrimaryKey
    @NonNull
    private String id;
    private String name, poster_path, backdrop_path;

    protected BelongsToCollection(Parcel in) {
        id = in.readString();
        name = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
    }

    public static final Creator<BelongsToCollection> CREATOR = new Creator<BelongsToCollection>() {
        @Override
        public BelongsToCollection createFromParcel(Parcel in) {
            return new BelongsToCollection(in);
        }

        @Override
        public BelongsToCollection[] newArray(int size) {
            return new BelongsToCollection[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
    }
}
