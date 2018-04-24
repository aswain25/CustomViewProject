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
import android.widget.TextView;

public class CheckeredEditText extends android.support.v7.widget.AppCompatEditText
{

    public static final String TAG = "MyRactangleTextView";
    int fillColor;
    private int width;
    private int height;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CheckeredEditText(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CheckeredEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CheckeredEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(Context context, @NonNull AttributeSet attrs, int defStyleRed, int defStyleRes)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRectangle);

        fillColor = typedArray.getColor(R.styleable.MyRectangle_fillColor, 0xFF000000);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        Log.d(TAG, "onDraw: ");
        Paint paint = new Paint();
        paint.setColor(fillColor);

        //clears the view
        for (int i = 0; i * height < width; i+=2)
            canvas.drawRect(new Rect(i * height, 0, i * height + height, height), paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desiredHeight = 200;
        int desiredWidth = 200;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
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
