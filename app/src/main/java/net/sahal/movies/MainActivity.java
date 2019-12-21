package net.sahal.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.*;
import androidx.room.Room;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.*;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.sahal.movies.Adapter.MoviesAdapter;
import net.sahal.movies.Models.MoviesList;
import net.sahal.movies.Models.Results;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rView;

    private MoviesAdapter mAdapter;
    private Api api;
    private ArrayList<Results> List = new ArrayList<>();
    private FavMoviesDAO moviesDAO;
    private RecyclerView.LayoutManager mLayoutManager;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MoviesDatabase database = Room.databaseBuilder(this, MoviesDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        this.moviesDAO = database.getfavMoviesDAO();
        this.savedInstanceState = savedInstanceState;

        if (isNetworkConnected()) {
            callRetrofit(R.string.pop);

        } else {
            callRetrofit(R.string.fav);
            Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void generateList() {
        mAdapter = new MoviesAdapter(List);
        rView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 3);
        mLayoutManager.onSaveInstanceState();
        rView.setLayoutManager(mLayoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
        rView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            rView.post(new Runnable() {
                @Override
                public void run() {
                    int pos = savedInstanceState.getInt("pos");
                    rView.scrollToPosition(pos);
                }
            });
        }
        rView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Results movie = List.get(position);
                launchDetailActivity(movie, getSupportActionBar().getTitle() + "");
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }

    private void launchDetailActivity(Results result, String whatever) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("whatever", whatever);
        intent.putExtra("Results", (Parcelable) result);
        startActivityForResult(intent, 1);
    }

    private void callRetrofit(int Name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        Call<MoviesList> call;
        if (Name == R.string.pop) {
            getSupportActionBar().setTitle(R.string.pop);
            call = api.getMovies("popular", BuildConfig.MOVIE_API_KEY);
            call.enqueue(new Callback<MoviesList>() {
                @Override
                public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                    List = response.body().getResults();
                    mAdapter.setLest(List);
                }

                @Override
                public void onFailure(Call<MoviesList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                }
            });

        } else if (Name == R.string.top) {
            getSupportActionBar().setTitle(R.string.top);
            call = api.getMovies("top_rated", BuildConfig.MOVIE_API_KEY);
            call.enqueue(new Callback<MoviesList>() {
                @Override
                public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                    List = response.body().getResults();
                    mAdapter.setLest(List);
                }

                @Override
                public void onFailure(Call<MoviesList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                }
            });

        } else if (Name == R.string.fav) {
            getSupportActionBar().setTitle(R.string.fav);
            List.clear();
            moviesDAO.getAllFavMovies().observe(this, new Observer<java.util.List<Results>>() {
                @Override
                public void onChanged(java.util.List<Results> results) {
                    mAdapter.setLest(results);
                }
            });
        }
        generateList();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (getSupportActionBar().getTitle().equals("Favorite Movies")) {
                callRetrofit(R.string.fav);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pop:
                callRetrofit(R.string.pop);
                return true;
            case R.id.top:
                callRetrofit(R.string.top);
                return true;
            case R.id.fav:
                callRetrofit(R.string.fav);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("pos", mAdapter.getAdapterPosition());
        super.onSaveInstanceState(savedInstanceState);
    }
}