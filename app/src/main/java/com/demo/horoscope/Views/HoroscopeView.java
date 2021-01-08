package com.demo.horoscope.Views;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.horoscope.Models.HoroscopeResponseModel;
import com.demo.horoscope.R;
import com.demo.horoscope.Utils.HoroscopeGestureDetector;

public class HoroscopeView {
    private static HoroscopeView INSTANCE;

    private RelativeLayout relativeLayout;
    private Context context;
    private View view;
    private HoroscopeResponseModel responseModel;

    private TextView moodTxt, colorTxt, numberTxt, timeTxt, compatTxt, descTxt;
    private ImageView closeImg;
    private LinearLayout linearLayout;

    private onClosedListener onClosedListener;


    public static HoroscopeView getInstance(Context context, RelativeLayout relativeLayout) {
        if (INSTANCE == null) {
            INSTANCE = new HoroscopeView(context, relativeLayout);
        }
        return INSTANCE;
    }

    private HoroscopeView(Context context, RelativeLayout relativeLayout){
        this.context = context;
        this.relativeLayout = relativeLayout;
    }

    public void setData(HoroscopeResponseModel responseModel){
        this.responseModel = responseModel;
        moodTxt = view.findViewById(R.id.moodViewTxt);
        numberTxt = view.findViewById(R.id.numberViewTxt);
        colorTxt = view.findViewById(R.id.colorViewTxt);
        closeImg = view.findViewById(R.id.exit);
        timeTxt = view.findViewById(R.id.timeViewTxt);
        compatTxt = view.findViewById(R.id.compatViewTxt);
        descTxt = view.findViewById(R.id.descViewTxt);
        linearLayout = view.findViewById(R.id.horoscopeContainer);

        linearLayout.setOnTouchListener(new HoroscopeGestureDetector(context){
            @Override
            public void onSwipeDown() {
                hide();
                super.onSwipeDown();
            }

            @Override
            public void onSwipeUp() {
                hide();
                super.onSwipeUp();
            }
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
        moodTxt.setText(responseModel.mood);
        colorTxt.setText(responseModel.color);
        numberTxt.setText(responseModel.number);
        timeTxt.setText(responseModel.time);
        compatTxt.setText(responseModel.compat);
        descTxt.setText("  " + responseModel.description);
    }

    public void setOnClosedListener(onClosedListener onClosedListener){
        this.onClosedListener = onClosedListener;
    }

    public void show(){
        view = LayoutInflater.from(context).inflate(R.layout.horoscope_view, null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(params);
        relativeLayout.addView(view);
    }

    public void hide(){
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                1f, 0f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(500);
        view.startAnimation(anim);
        view.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                relativeLayout.removeView(view);
                if (onClosedListener != null){
                    onClosedListener.close();
                }
            }
        });
    }

    public interface onClosedListener{
        void close();
    }
}
