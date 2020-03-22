package com.example.diyviewstudy.view.refreshView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class MyHeaderView extends View implements IHeaderView {
    /**
     * 状态
     * 0 初始状态
     * 1 开始下拉
     * 2 开始生效
     * 3 正在加载
     * 4 结束
     */
    int state = 0;
    /**
     * 开始阶段的进度 0~1
     */
    float startFac = 0;
    /**
     * 生效阶段的进度 0~1
     */
    float effectFac = 0;

    /**
     * 加载中动画变化的值
     */
    int loadingAnimValue = 0;

    Paint mPaint;
    Path mPath;
    private ValueAnimator loadingAnimator;

    public MyHeaderView(Context context) {
        super(context);
        init();
    }

    public MyHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (state) {
            case 0:
                drawDefault(canvas);
                break;
            case 1:
                drawStart(canvas);
                break;
            case 2:
                drawEffect(canvas);
                break;
            case 3:
                drawLoading(canvas);
                break;
            case 4:
                drawEnd(canvas);
                break;
        }
    }

    private void drawDefault(Canvas canvas) {

    }

    private void drawStart(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(getWidth() / 2, getHeight() - 20 - startFac * 200);
        mPath.lineTo(getWidth() / 2 - 100, getHeight() - 20);
        mPath.lineTo(getWidth() / 2 + 100, getHeight() - 20);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    private void drawEffect(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(getWidth() / 2 - 100, getHeight() - 20 - effectFac * 200);
        mPath.lineTo(getWidth() / 2 + 100, getHeight() - 20 - effectFac * 200);
        mPath.lineTo(getWidth() / 2 + 100, getHeight() - 20);
        mPath.lineTo(getWidth() / 2 - 100, getHeight() - 20);

        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    private void drawLoading(Canvas canvas) {
        if (loadingAnimValue % 3 == 0) {
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2 - 100, getHeight() - 20, 10, mPaint);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth() / 2, getHeight() - 20, 10, mPaint);
            canvas.drawCircle(getWidth() / 2 + 100, getHeight() - 20, 10, mPaint);
        } else if (loadingAnimValue % 3 == 1) {
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth() / 2 - 100, getHeight() - 20, 10, mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2, getHeight() - 20, 10, mPaint);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth() / 2 + 100, getHeight() - 20, 10, mPaint);
        } else if (loadingAnimValue % 3 == 2) {
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getWidth() / 2 - 100, getHeight() - 20, 10, mPaint);
            canvas.drawCircle(getWidth() / 2, getHeight() - 20, 10, mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2 + 100, getHeight() - 20, 10, mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void drawEnd(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(getWidth() / 2 - 40, getHeight() - 20 + 40);
        mPath.lineTo(getWidth() / 2, getHeight() - 20);
        mPath.lineTo(getWidth() / 2 + 100, getHeight() - 20 + 100);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void inflate(int layoutId) {

    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onStart(float fac) {
        state = 1;
        this.startFac = fac;
        postInvalidate();
    }

    @Override
    public void onEffect(float fac) {
        state = 2;
        this.effectFac = fac;
        postInvalidate();
    }

    @Override
    public void onLoading() {
        if (state != 3) {
            state = 3;
            postInvalidate();
            startLoadingAnimation();
        }
    }

    @Override
    public void onFinish() {
        stopLoadingAnimation();
        state = 4;
        postInvalidate();
    }


    private void startLoadingAnimation() {
        loadingAnimator = ValueAnimator.ofInt(0, 1, 2);
        loadingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                loadingAnimValue = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        loadingAnimator.setDuration(1000);
        loadingAnimator.setInterpolator(new LinearInterpolator());
        loadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        loadingAnimator.start();
    }

    private void stopLoadingAnimation() {
        Log.e(TAG, "结束加载动画 stopLoadingAnimation()");
        loadingAnimator.cancel();
    }

}
