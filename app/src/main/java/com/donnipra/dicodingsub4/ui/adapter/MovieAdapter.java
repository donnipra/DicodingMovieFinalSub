package com.donnipra.dicodingsub4.ui.adapter;

/*
 * Created by donni.
 * Last modified 6/12/18 10:32 AM.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.donnipra.dicodingsub4.R;
import com.donnipra.dicodingsub4.model.MovieItemModel;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>  {

    private List<MovieItemModel> list = new ArrayList<>();

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void replaceAll(List<MovieItemModel> items) {
        list.clear();
        list = items;
        notifyDataSetChanged();
    }

    public void updateData(List<MovieItemModel> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_items, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
