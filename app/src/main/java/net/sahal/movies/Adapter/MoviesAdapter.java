package net.sahal.movies.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import net.sahal.movies.R;
import net.sahal.movies.Models.Results;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private ArrayList<Results> List;
    public MoviesAdapter.MyViewHolder myHolder = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.img)
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public MoviesAdapter(ArrayList<Results> List) {
        this.List = List;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        this.myHolder = holder;
        Results movie = List.get(position);
        holder.title.setText(movie.getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w200/" + movie.getPoster_path())
                .placeholder(R.drawable.no_image).error(R.drawable.no_image).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public int getAdapterPosition() {
        return myHolder != null ? myHolder.getAdapterPosition() : 0;
    }

    public void setLest(java.util.List<Results> Lest) {
        List.clear();
        for (int i = 0; i < Lest.size(); i++) {
            List.add(Lest.get(i));
        }
        notifyDataSetChanged();
    }
}
