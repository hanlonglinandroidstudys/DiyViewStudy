package com.example.diyviewstudy.view.screenMoveView;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.diyviewstudy.R;

/**
 * author: DragonForest
 * time: 2019/12/16
 */
public class ScreenMoveView extends View {
    Paint mPaint;
    Bitmap mBitmap;

    float lastX;
    float lastY;
    private int screenWidth;
    private int screenHeight;

    public ScreenMoveView(Context context) {
        super(context);
        init();
    }

    public ScreenMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initScreenParams();
        Log.e("ScreenMoveView", "screenHeight&Width," + screenHeight + "," + screenWidth);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
    }

    private void initScreenParams() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();

        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            int statusBarHeight = getResources().getDimensionPixelSize(height);
            screenHeight -= statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                setX(getX() + event.getRawX() - lastX);
                setY(getY() + event.getRawY() - lastY);
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = getX() + event.getRawX() - lastX;
                float currentY = getY() + event.getRawY() - lastY;
                Log.e("ScreenMoveView", currentX + "," + currentY + "," + getX() + "," + getY());
                if (currentX < 0) {
                    currentX = -getWidth() / 2;
                }
                if (currentX > (screenWidth - getWidth())) {
                    currentX = screenWidth - getWidth() / 2;
                }
                if (currentY < 0) {
                    currentY = -getHeight() / 2;
                }
                if (currentY > (screenHeight - getHeight())) {
                    currentY = screenHeight - getHeight() / 2;
                }
                Log.e("ScreenMoveView", "result" + currentX + "," + currentY);
                animateTo(getX(), getY(), currentX, currentY);
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 动画方式移动到指定位置
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    private void animateTo(float startX, float startY, float endX, float endY) {
        ValueAnimator animatorX = ValueAnimator.ofFloat(startX, endX);
        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                setX(x);
            }
        });
        ValueAnimator animatorY = ValueAnimator.ofFloat(startY, endY);
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
                setY(y);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.setDuration(200);
        animatorSet.start();
    }
}
