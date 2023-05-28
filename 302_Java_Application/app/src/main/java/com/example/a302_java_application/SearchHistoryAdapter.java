package com.example.a302_java_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchHistoryAdapter extends RecyclerView.Adapter<com.example.a302_java_application.SearchHistoryAdapter.SearchHistoryViewHolder> {

    ArrayList<String> searchHistory;
    Context context;

    public SearchHistoryAdapter(ArrayList<String> albums, Context context) {

        this.searchHistory = albums;
        this.context = context;

    }

    @Override
    public com.example.a302_java_application.SearchHistoryAdapter.SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Create inflater
        LayoutInflater inflater = LayoutInflater.from(context);
//        Create view
        View view = inflater.inflate(R.layout.search_history_layout, parent, false);

//        Return the ViewHolder
        return new com.example.a302_java_application.SearchHistoryAdapter.SearchHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.a302_java_application.SearchHistoryAdapter.SearchHistoryViewHolder holder, int position) {

//        Bind album data to layout defined by ViewHolder
        holder.name.setText(searchHistory.get(position));

    }

    @Override
    public int getItemCount() {

//        Return number of items
        return searchHistory.size();
    }

    public static class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public SearchHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

//            Define the views in the layout we will be adapting
            name = itemView.findViewById(R.id.album_name);
        }
    }
}

