package com.example.diyviewstudy.view.headView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.diyviewstudy.R;

/**
 * 头像控件
 * <p>
 * author: DragonForest
 * time: 2019/12/9
 */
public class HeadView extends View {
    Bitmap mBitmap;
    Paint mPaint;
    BitmapShader mBitmapShader;

    public HeadView(Context context) {
        super(context);
        init();
    }

    public HeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float scale = (float) getWidth() / mBitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(matrix);
        int cr = Math.min(getWidth(), getHeight()) / 2;

        // 画圆形头像
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, cr, mPaint);
        // 画矩形头像
//        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);

    }
}
