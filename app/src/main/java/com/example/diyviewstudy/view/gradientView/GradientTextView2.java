package com.example.diyviewstudy.view.gradientView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 渐变文字 通过继承TextView实现
 * <p>
 * author: DragonForest
 * time: 2019/12/9
 */
public class GradientTextView2 extends AppCompatTextView {
    Paint mPaint;

    // 移动长度
    int dx;
    // 文字长度
    private float length;
    private LinearGradient linearGradient;

    public GradientTextView2(Context context) {
        super(context);
        init();
    }

    public GradientTextView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        /*
            此处必须通过getPaint() 获取TextView的画笔，后面才能将shader设置到此画笔上， 自己新建的画笔对TextView的文字是无效的
         */
        mPaint = getPaint();
        length = mPaint.measureText(getText().toString());
        linearGradient = new LinearGradient(-length, 0, 0, 0, Color.WHITE, Color.BLACK, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(dx, 0);
        linearGradient.setLocalMatrix(matrix);
        mPaint.setShader(linearGradient);
        super.onDraw(canvas);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, (int) length * 2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }
}
