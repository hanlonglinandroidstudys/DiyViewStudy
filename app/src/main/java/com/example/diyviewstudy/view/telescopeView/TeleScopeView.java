package com.example.diyviewstudy.view.telescopeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.diyviewstudy.R;

/**
 * 望远镜
 * <p>
 * author: DragonForest
 * time: 2019/12/9
 */
public class TeleScopeView extends View {
    Bitmap mBitmap;
    Bitmap mCacheBitmap;
    Paint mPaint;
    BitmapShader mBitmapShader;

    // 圆心
    int cx;
    int cy;
    // 半径
    int cr = 100;

    public TeleScopeView(Context context) {
        super(context);
        init();
    }

    public TeleScopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_img);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mCacheBitmap == null) {
//            mCacheBitmap = Bitmap.createBitmap(mBitmap, 0, 0, getWidth(), getHeight());
            mCacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas cacheCanvas = new Canvas(mCacheBitmap);
            cacheCanvas.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        }
        if(mBitmapShader==null){
            mBitmapShader=new BitmapShader(mCacheBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix matrix=new Matrix();
//            matrix.setScale(2,2);
            mBitmapShader.setLocalMatrix(matrix);
        }
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(cx, cy, cr, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cx = (int) event.getX();
                cy = (int) event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                cx = (int) event.getX();
                cy = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
