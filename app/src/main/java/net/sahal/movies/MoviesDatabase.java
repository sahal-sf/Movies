package net.sahal.movies;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.sahal.movies.Models.Results;

@Database(entities = {Results.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})

public abstract class MoviesDatabase extends RoomDatabase {
    private static MoviesDatabase INSTANCE;
    private static final String DB_NAME = "movies.db";

    public abstract FavMoviesDAO getfavMoviesDAO();

    public static MoviesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("MoviesDatabase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        public PopulateDbAsync(MoviesDatabase instance) {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
