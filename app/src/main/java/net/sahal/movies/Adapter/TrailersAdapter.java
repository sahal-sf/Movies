package net.sahal.movies.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.sahal.movies.R;
import net.sahal.movies.Models.Results;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.MyViewHolder> {

    private ArrayList<Results> List;

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

    public TrailersAdapter(ArrayList<Results> List) {
        this.List = List;
    }

    @Override
    public TrailersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers_list_row, parent, false);
        return new TrailersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrailersAdapter.MyViewHolder holder, int position) {
        Results movie = List.get(position);
        holder.title.setText(movie.getName());
        holder.img.setImageResource(R.drawable.play_icon);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
