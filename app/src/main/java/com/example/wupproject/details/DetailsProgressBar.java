package com.example.wupproject.details;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class DetailsProgressBar extends ProgressBar {
    private Paint textPaint;

    public DetailsProgressBar(Context context) {
        super(context);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
    }

    public DetailsProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
    }

}
