package com.donnipra.dicodingsub4.util;

/*
 * Created by donni.
 * Last modified 6/12/18 1:56 PM.
 */

import android.view.View;

public class ItemClickSupport implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;
    public ItemClickSupport(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
    }
    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
