package com.example.a302_java_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.RecyclerListHolder> {

    ArrayList<Album> albums;
    Context context;
    private final RecyclerListInterface recyclerListInterface;
    boolean[] favouriteAlbums = new boolean[30];

    public RecyclerListAdapter (ArrayList<Album> albums, Context context, RecyclerListInterface recyclerListInterface) {
        this.albums = albums;
        this.context = context;
        this.recyclerListInterface = recyclerListInterface;
    }

    @Override
    public RecyclerListAdapter.RecyclerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create inflater
        LayoutInflater inflater = LayoutInflater.from(context);
        // Create view
        View view = inflater.inflate(R.layout.recycler_list_layout, parent, false);

        // Return the ViewHolder
        return new RecyclerListAdapter.RecyclerListHolder(view, recyclerListInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerListAdapter.RecyclerListHolder holder, int position) {

        int pos = position;

        // Bind album data to layout defined by ViewHolder
        holder.name.setText(albums.get(position).getName());
        holder.image.setImageResource(albums.get(position).getImage());
        holder.artist.setText(albums.get(position).getArtist());
        holder.releaseDate.setText(albums.get(position).getReleaseDate());

        if (albums.get(pos).isLiked() == false) {
            holder.favourite.setImageResource(R.drawable.heart_stroke);
        } else {
            holder.favourite.setImageResource(R.drawable.heart_fill);
        }

        // Change the status of favourite button
        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (albums.get(pos).isLiked() == false) {
                    holder.favourite.setImageResource(R.drawable.heart_fill);
                    albums.get(pos).setLikedToTrue();
                    favouriteAlbums[pos] = true;
                } else {
                    holder.favourite.setImageResource(R.drawable.heart_stroke);
                    albums.get(pos).setLikedToFalse();
                    favouriteAlbums[pos] = false;
                }
            }
        });
    }

    public boolean[] getFavouriteAlbums() {
        return favouriteAlbums;
    }

    @Override
    public int getItemCount() {

        // Return number of items
        return albums.size();
    }

    public static class RecyclerListHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView artist, releaseDate;
        ImageButton favourite;

        public RecyclerListHolder(@NonNull View itemView, RecyclerListInterface recyclerListInterface) {
            super(itemView);

            // Define the views in the layout we will be adapting
            image = itemView.findViewById(R.id.album_cover);
            name = itemView.findViewById(R.id.album_name);
            artist = itemView.findViewById(R.id.group_name);
            releaseDate = itemView.findViewById(R.id.release_date);
            favourite = itemView.findViewById(R.id.favourite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerListInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION) {
                            recyclerListInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}