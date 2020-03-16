package com.example.uuj.moviebuddy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Owner on 10/03/2020.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {

    private Context thecontext;
    private List<Movie> movieList;

    public MovieAdapter(Context thecontext, List<Movie> movieList)  {

        this.thecontext = thecontext;
        this.movieList = movieList;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView title, userrating;
        public ImageView thumbnail;

        public Viewholder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titlee);
            userrating = (TextView) view.findViewById(R.id.ratingg);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnaill);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)   {
                        Movie clickedItem = movieList.get(position);
                        Intent intent = new Intent(thecontext, DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(position).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(position).getPosterPath());
                        intent.putExtra("overview", movieList.get(position).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(position).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(position).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        thecontext.startActivity(intent);
                        Toast.makeText(v.getContext(), "Clicked " + clickedItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    @Override
    public MovieAdapter.Viewholder onCreateViewHolder(ViewGroup viewGroup, int i)   {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.Viewholder viewHolder, int i)   {
        viewHolder.title.setText(movieList.get(i).getOriginalTitle());
        String vote = Double.toString(movieList.get(i).getVoteAverage());
        viewHolder.userrating.setText(vote);

        Glide.with(thecontext).load(movieList.get(i).getPosterPath()).placeholder(R.drawable.ic_action_load).into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount()   {
        return movieList.size();
    }

}
