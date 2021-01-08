package com.demo.horoscope.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.demo.horoscope.R;

public class LoadingView {

    private static LoadingView INSTANCE;

    private RelativeLayout relativeLayout;
    private Context context;
    private View view;





    public static LoadingView getInstance(Context context, RelativeLayout relativeLayout) {
        if (INSTANCE == null) {
            INSTANCE = new LoadingView(context, relativeLayout);
        }
        return INSTANCE;
    }

    private LoadingView(Context context, RelativeLayout relativeLayout){
        this.context = context;
        this.relativeLayout = relativeLayout;
    }

    public void show(){
        view = LayoutInflater.from(context).inflate(R.layout.progress_view, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(params);
        relativeLayout.addView(view);
    }

    public void hide(){
        relativeLayout.removeView(view);
    }
}
