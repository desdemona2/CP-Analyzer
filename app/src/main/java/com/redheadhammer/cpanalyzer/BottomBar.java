package com.redheadhammer.cpanalyzer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;

import java.util.zip.Inflater;

public class BottomBar {
    private Context context;
    private View view;
    private ImageView home, single, history;

    public BottomBar(Context context, @LayoutRes int resource) {
        this.context = context;
        this.view = View.inflate(context, resource, null);
    }

    private void attach() {

    }
}
