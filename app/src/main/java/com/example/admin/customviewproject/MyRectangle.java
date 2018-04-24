package com.example.admin.customviewproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyRectangle extends View
{

    public static final String TAG = "MyRectangle";
    int fillColor;
    private int width;
    private int height;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyRectangle(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyRectangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyRectangle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(Context context, @NonNull AttributeSet attrs, int defStyleRed, int defStyleRes)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRectangle);

        fillColor = typedArray.getColor(R.styleable.MyRectangle_fillColor, 0xFF00FF00);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        Paint paint = new Paint();
        paint.setColor(fillColor);


        //clears the view
        canvas.drawColor(fillColor);
        //canvas.drawRect(new Rect(0, 0, width, height), paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desiredHeight = 200;
        int desiredWidth = 200;

        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);


        //Measure Width
        if (widthMode == View.MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == View.MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == View.MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == View.MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        Log.d(TAG, "onMeasure: width" + width);
        Log.d(TAG, "onMeasure: height" + height);

        setMeasuredDimension(width, height);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
        invalidate();
    }
}
