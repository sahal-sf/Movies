package net.sahal.movies.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.sahal.movies.Models.Results;
import net.sahal.movies.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private ArrayList<Results> List;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.author)
        public TextView author;
        @BindView(R.id.content)
        public TextView content;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ReviewsAdapter(ArrayList<Results> List) {
        this.List = List;
    }

    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_list_row, parent, false);
        return new ReviewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewsAdapter.MyViewHolder holder, int position) {
        Results movie = List.get(position);
        holder.author.setText(movie.getAuthor());
        holder.content.setText(movie.getContent());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
