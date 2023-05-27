package com.example.a302_java_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    ArrayList<Album> albums;
    Context context;

    public RecyclerViewAdapter (ArrayList<Album> albums, Context context) {

        this.albums = albums;
        this.context = context;

    }

    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Create inflater
        LayoutInflater inflater = LayoutInflater.from(context);
//        Create view
        View view = inflater.inflate(R.layout.recycler_item_layout, parent, false);

//        Return the ViewHolder
        return new RecyclerViewAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {

//        Bind album data to layout defined by ViewHolder
        holder.name.setText(albums.get(position).getName());
        holder.image.setImageResource(albums.get(position).getImage());

    }

    @Override
    public int getItemCount() {

//        Return number of items
        return albums.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

//            Define the views in the layout we will be adapting
            image = itemView.findViewById(R.id.album_cover);
            name = itemView.findViewById(R.id.album_name);
        }
    }
}
