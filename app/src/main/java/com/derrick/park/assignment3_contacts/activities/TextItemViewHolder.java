package com.derrick.park.assignment3_contacts.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class TextItemViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public TextItemViewHolder(@NonNull TextView textView) {
        super(textView);

        this.textView = textView;
    }

}
