package com.example.diyviewstudy.view.screenMoveView;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.diyviewstudy.R;

/**
 * author: DragonForest
 * time: 2019/12/16
 */
public class ScreenMoveView2 extends View {
    Paint mPaint;
    Bitmap mBitmap;

    float lastX;
    float lastY;
    private int screenWidth;
    private int screenHeight;

    WindowManager windowManager;
    WindowManager.LayoutParams windowLayoutParams;

    public ScreenMoveView2(Context context) {
        super(context);
        init(context);
    }

    public ScreenMoveView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void attachToWindow() {
        windowLayoutParams.x = 100;
        windowLayoutParams.y = 100;
        windowLayoutParams.width = 200;
        windowLayoutParams.height = 200;
        windowLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        windowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        // 此flag表示此window下层的其他window接收事件不受影响， 不然其他window事件会被此window覆盖
        windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        windowManager.addView(this, windowLayoutParams);
    }

    private void init(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowLayoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSLUCENT);
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
                return true;
            case MotionEvent.ACTION_MOVE:
                windowLayoutParams.x = (int) event.getRawX() - windowLayoutParams.width / 2;
                windowLayoutParams.y = (int) event.getRawY() - windowLayoutParams.height / 2;
                windowManager.updateViewLayout(this, windowLayoutParams);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

}
