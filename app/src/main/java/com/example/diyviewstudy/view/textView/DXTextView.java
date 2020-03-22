package com.example.diyviewstudy.view.textView;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * author: DragonForest
 * time: 2020/3/6
 */
public class DXTextView extends AppCompatTextView {

    private TextPaint mPaint;
    Matrix matrix = new Matrix();
    // 0-1
    float moveXP = 0;
    float moveYP = 0;
    private ObjectAnimator animator;

    public DXTextView(Context context) {
        super(context);
    }

    public DXTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    private void init() {
        mPaint = getPaint();
        animator = ObjectAnimator.ofFloat(this, "moveXP", 0, 1);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new DecelerateInterpolator());
    }

    /**
     * 下划线
     *
     * @param isunderline
     */
    public void setUnderline(boolean isunderline) {
        if (isunderline) {
            mPaint.setUnderlineText(true);
            postInvalidate();
        }
    }

    /**
     * 删除线
     *
     * @param isStrikeThru
     */
    public void setStrikeThruText(boolean isStrikeThru) {
        if (isStrikeThru) {
            mPaint.setStrikeThruText(true);
            postInvalidate();
        }
    }

    /**
     * 渐变
     *
     * @param color0
     * @param color1
     */
    public void setGradient(int color0, int color1) {
        LinearGradient linearGradient = new LinearGradient(0, getHeight() / 2, 200, getHeight() / 2, color0, color1, Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);
        postInvalidate();
    }

    public void setMoveXP(float moveXP) {
        this.moveXP = moveXP;
        postInvalidate();
    }

    public void setMoveYP(float moveYP) {
        this.moveYP = moveYP;
        postInvalidate();
    }

    public void startXMove() {
        animator = ObjectAnimator.ofFloat(this, "moveXP", -1, 0);
        animator.setDuration(3000);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }

    public void startYMove() {
        animator = ObjectAnimator.ofFloat(this, "moveYP", -1, 0);
        animator.setDuration(3000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        matrix.reset();
        matrix.preTranslate(moveXP * getWidth(), moveYP * getHeight());
        canvas.concat(matrix);
        super.onDraw(canvas);
        canvas.restore();
    }
}
