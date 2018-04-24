package com.qrkay.www.qrkay.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.qrkay.www.qrkay.R;

public class StampView extends View {

    private boolean mIsStamped;
    private int mStampStyle;
    private Paint mStampedPaint;
    private Paint mStampBackground;
    private Paint mStampStroke;
    private RectF mBackgroundBounds;
    private RectF mStampBounds;

    public boolean isStamped(){
        return mIsStamped;
    }

    public int getStampStyle(){ return mStampStyle; }

    public void setStamped(boolean stamped){
        mIsStamped = stamped;
        invalidate();
        requestLayout();
    }

    public void setStampStyle(int style){
        mStampStyle = style;
        invalidate();
        requestLayout();
    }

    public StampView(Context context, AttributeSet attrs){
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StampView,
                0, 0);

        try{
            mIsStamped = a.getBoolean(R.styleable.StampView_isStamped, false);
            mStampStyle = a.getInt(R.styleable.StampView_stampStyle, 0);
        }finally {
            a.recycle();
        }

        init();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // draw background
        if(getStampStyle() == 0){
            canvas.drawOval(mBackgroundBounds, mStampBackground);
            canvas.drawOval(mBackgroundBounds, mStampStroke);
        }else if(getStampStyle() == 1){
            Resources r = this.getResources();
            int rxy = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
            canvas.drawRoundRect(mBackgroundBounds, rxy, rxy, mStampBackground);
            canvas.drawRoundRect(mBackgroundBounds, rxy, rxy, mStampStroke);
        }else{
            canvas.drawRect(mBackgroundBounds, mStampBackground);
            canvas.drawRect(mBackgroundBounds, mStampStroke);
        }

        if(isStamped()){
            canvas.drawLine(mStampBounds.left, mStampBounds.top, mStampBounds.right, mStampBounds.bottom, mStampedPaint);
            canvas.drawLine(mStampBounds.right, mStampBounds.top, mStampBounds.left, mStampBounds.bottom, mStampedPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = Math.max(minw, MeasureSpec.getSize(widthMeasureSpec));

        setMeasuredDimension(w, w);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        float ww = (float) w - xpad;
        float hh = (float) h - ypad;
        float diameter = Math.min(ww, hh);
        mBackgroundBounds = new RectF(0.0f, 0.0f, diameter, diameter);
        mBackgroundBounds.offsetTo(getPaddingLeft(), getPaddingTop());

        mStampBounds = new RectF(0.0f, 0.0f, diameter, diameter);
        mStampBounds.offset(getPaddingLeft(), getPaddingTop());
    }

    private void init(){
        setLayerToSW(this);
        // Set up paint for background
        mStampBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStampBackground.setColor(Color.WHITE);
        mStampBackground.setStyle(Paint.Style.FILL);

        mStampStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStampStroke.setColor(Color.BLACK);
        mStampStroke.setStyle(Paint.Style.STROKE);
        mStampStroke.setStrokeWidth(4);

        // Set up paint for stamp
        mStampedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStampedPaint.setStyle(Paint.Style.STROKE);
        mStampedPaint.setStrokeWidth(8);
        mStampedPaint.setColor(Color.RED);
    }

    private void setLayerToSW(View v) {
        if(!v.isInEditMode()){
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    private void setLayerToHW(View v){
        if(!v.isInEditMode()){
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
    }
}
