package net.sahal.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.sahal.movies.Adapter.ReviewsAdapter;
import net.sahal.movies.Adapter.TrailersAdapter;
import net.sahal.movies.Models.Results;
import net.sahal.movies.Models.ReviewsList;
import net.sahal.movies.Models.TrailersList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.favbutton)
    CheckBox fav;
    @BindView(R.id.imageView)
    ImageView img;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.runtime)
    TextView runtime;
    @BindView(R.id.trailer_rv)
    RecyclerView trailerView;
    @BindView(R.id.reviews_rv)
    RecyclerView reviewView;

    private Api api;
    private Results movieDetails;
    private TrailersAdapter trailerAdapter;
    private ReviewsAdapter reviewAdapter;
    private String whatever = "";
    private FavMoviesDAO moviesDAO;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        MoviesDatabase database = Room.databaseBuilder(this, MoviesDatabase.class, "db-contacts")
                .allowMainThreadQueries()
                .build();
        moviesDAO = database.getfavMoviesDAO();

        intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        whatever = intent.getStringExtra("whatever");
        movieDetails = intent.getParcelableExtra("Results");

        if (movieDetails != null) {
            if (moviesDAO.getFavMovies(movieDetails.getId()) == null) {
                fav.setChecked(false);
            } else {
                fav.setChecked(true);
            }
            if (whatever.equals("Favorite Movies") && !isNetworkConnected()) {
                generateList();
            } else {
                callRetrofit("Data");
            }
        }
    }

    private void generateList() {
        getSupportActionBar().setTitle(movieDetails.getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w185/" + movieDetails.getPoster_path())
                .placeholder(R.drawable.no_image).error(R.drawable.no_image).into(img);
        date.setText(movieDetails.getRelease_date().split("-")[0]);
        runtime.setText(movieDetails.getRuntime() + " min");
        rating.setText(movieDetails.getVote_average() + " / 10");
        description.setText(movieDetails.getOverview());

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fav.isChecked()) {
                    moviesDAO.insertFavMovies(movieDetails);
                    fav.setChecked(true);

                } else {
                    moviesDAO.deleteFavMovies(movieDetails.getId());
                    fav.setChecked(false);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        callRetrofit("Trailers");
        callRetrofit("Reviews");
    }

    private void generateTrailersList() {
        trailerAdapter = new TrailersAdapter(movieDetails.getTrailerList());
        trailerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        trailerView.setLayoutManager(mLayoutManager);
        trailerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        trailerView.setItemAnimator(new DefaultItemAnimator());
        trailerView.setAdapter(trailerAdapter);

        trailerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), trailerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        watchYoutubeVideo(getApplicationContext(), movieDetails.getTrailerList().get(position).getKey());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    private void generateReviewsList() {
        reviewAdapter = new ReviewsAdapter(movieDetails.getReviewList());
        reviewView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        reviewView.setLayoutManager(mLayoutManager);
        reviewView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        reviewView.setItemAnimator(new DefaultItemAnimator());
        reviewView.setAdapter(reviewAdapter);
    }

    private void callRetrofit(String Type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        if (Type.equals("Data")) {
            Call<Results> call = api.getDetails(movieDetails.getId(), BuildConfig.MOVIE_API_KEY);
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    movieDetails = response.body();
                    generateList();
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                }
            });

        } else if (Type.equals("Trailers")) {
            Call<TrailersList> call = api.getVideos(movieDetails.getId(), BuildConfig.MOVIE_API_KEY);
            call.enqueue(new Callback<TrailersList>() {
                @Override
                public void onResponse(Call<TrailersList> call, Response<TrailersList> response) {
                    movieDetails.setTrailerList(response.body().getResults());
                    generateTrailersList();
                }

                @Override
                public void onFailure(Call<TrailersList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                }
            });

        } else if (Type.equals("Reviews")) {
            Call<ReviewsList> call = api.getReviews(movieDetails.getId(), BuildConfig.MOVIE_API_KEY);
            call.enqueue(new Callback<ReviewsList>() {
                @Override
                public void onResponse(Call<ReviewsList> call, Response<ReviewsList> response) {
                    movieDetails.setReviewList(response.body().getResults());
                    generateReviewsList();
                }

                @Override
                public void onFailure(Call<ReviewsList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}