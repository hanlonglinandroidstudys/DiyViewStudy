package com.example.diyviewstudy.view.shadowLayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 图形阴影效果
 *
 * author: DragonForest
 * time: 2019/12/9
 */
public class ShadowView extends View {

    Paint mPaint;

    // 是否显示阴影
    boolean isShowShadowLayer = true;

    public ShadowView(Context context) {
        super(context);
        init();
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 禁止硬件加速
        /*
            因为setShadowLayer函数 在开启硬件加速的情况下 只对文字阴影效果起作用，其他图形和图片的阴影都需要关闭硬件加速
         */
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        if (isShowShadowLayer) {
            mPaint.setShadowLayer(2, 5, 5, Color.GRAY);
        }else{
            // 清除阴影
            mPaint.clearShadowLayer();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLUE);
        // 画正方形
        canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), mPaint);
        // 画圆形
//        float r=Math.min(getWidth()-getPaddingLeft()-getPaddingRight(),getHeight()-getPaddingTop()-getPaddingBottom())/2;
//        canvas.drawCircle(getWidth()/2,getHeight()/2,r,mPaint);
        // 画文字
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.WHITE);
        String text = "点我试试";
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float textWidth = mPaint.measureText(text);
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom), mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isShowShadowLayer = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isShowShadowLayer = true;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
