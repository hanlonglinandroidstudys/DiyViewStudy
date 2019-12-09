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
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * 渐变文字 自己绘制文字
 *
 * author: DragonForest
 * time: 2019/12/9
 */
public class GradientTextView extends View {
    Paint mPaint;
    LinearGradient linearGradient;

    // 渐变开始位置x
    int gradientStartX;
    int gradientStartY;

    public GradientTextView(Context context) {
        super(context);
        init();
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.STROKE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(linearGradient==null){
            linearGradient=new LinearGradient(0,getHeight()/2,getWidth(),getHeight()/2, Color.WHITE,Color.BLACK, Shader.TileMode.CLAMP);
            mPaint.setShader(linearGradient);
        }
        Matrix matrix=new Matrix();
        matrix.setTranslate(gradientStartX,0);
        linearGradient.setLocalMatrix(matrix);
        mPaint.setShader(linearGradient);

        String text="渐变效果的文字";
        float textWidth = mPaint.measureText(text);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom),mPaint);
    }

    public void startAnim(){
        ValueAnimator animator=ValueAnimator.ofInt(0,500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                gradientStartX = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}
