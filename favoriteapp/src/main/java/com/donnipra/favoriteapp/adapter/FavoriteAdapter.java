package com.donnipra.favoriteapp.adapter;

/*
 * Created by donni.
 * Last modified 6/12/18 12:37 PM.
 */

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.donnipra.favoriteapp.BuildConfig;
import com.donnipra.favoriteapp.DetailActivity;
import com.donnipra.favoriteapp.R;
import com.donnipra.favoriteapp.model.FavoriteModel;
import com.donnipra.favoriteapp.util.DateTime;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.donnipra.favoriteapp.database.DatabaseContract.CONTENT_URI;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Cursor list;

    public FavoriteAdapter(Cursor items) {
        replaceAll(items);
    }

    public void replaceAll(Cursor items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_items, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private FavoriteModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new FavoriteModel(list);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_poster)
        ImageView img_poster;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_overview)
        TextView tv_overview;

        @BindView(R.id.tv_release_date)
        TextView tv_release_date;

        @BindView(R.id.btn_detail)
        Button btn_detail;

        @BindView(R.id.btn_share)
        Button btn_share;

        @BindView(R.id.cv_list)
        LinearLayout cv_list;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final FavoriteModel item) {
            tv_title.setText(item.getTitle());
            tv_overview.setText(item.getOverview());
            tv_release_date.setText(DateTime.getLongDate(item.getReleaseDate()));
            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG + "w154" + item.getPosterPath())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_photo_album_black)
                            .centerCrop()
                    )
                    .into(img_poster);

            btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.setData(Uri.parse(CONTENT_URI + "/" + item.getId()));
                    itemView.getContext().startActivity(intent);
                }
            });

            btn_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TITLE, item.getTitle());
                    intent.putExtra(Intent.EXTRA_SUBJECT, item.getTitle());
                    intent.putExtra(Intent.EXTRA_TEXT, item.getTitle() + "\n\n" + item.getOverview());
                    itemView.getContext().startActivity(Intent.createChooser(intent, itemView.getResources().getString(R.string.label_share)));
                }
            });

            cv_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.setData(Uri.parse(CONTENT_URI + "/" + item.getId()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
