package com.example.diyviewstudy.view.dymapView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 大有 地图控件
 * 功能：
 * 1.标点
 * 2.放大查看
 * 3.路线
 * <p>
 * author: DragonForest
 * time: 2019/12/20
 */
public class DyMapView extends View {
    /**
     * 地图图片
     */
    private Bitmap mBitmap = null;

    private Paint mPaint;

    private Matrix matrix;
    private float mScale;

    List<MapMark> markList;

    public DyMapView(Context context) {
        super(context);
        init();
    }

    public DyMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        matrix = new Matrix();

        markList = new ArrayList<>();
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        postInvalidate();
    }

    private void adjustBitmapSize() {
        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        int width = getWidth();
        int height = getHeight();

        float mScaleX = 1;
        float mScaleY = 1;
        if (bitmapWidth > width) {
            mScaleX = (float) width / bitmapWidth;
        }
        if (bitmapHeight > height) {
            mScaleY = (float) height / bitmapHeight;
        }
        mScale = Math.min(mScaleX, mScaleY);
        matrix.setScale(mScale, mScale);

        Log.e("DyMapView", "bitmapWidth,bitmapHeight:" + bitmapWidth + "," + bitmapHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap != null) {
            adjustBitmapSize();
            canvas.drawBitmap(mBitmap, matrix, mPaint);
        }
        if (markList != null && markList.size() > 0) {
            // 画标点
            for (int i = 0; i < markList.size(); i++) {
                drawMark(canvas, markList.get(i));
            }
        }
    }

    private void drawMark(Canvas canvas, MapMark mapMark) {
        // 转化xy
        int rx = (int) (mapMark.x * mScale);
        int ry = (int) (mapMark.y * mScale);
        Log.e("DyMapView:", "rx:ry:" + rx + "," + ry);
        // 画出来
        canvas.drawCircle(rx, ry, 10, mPaint);
    }

    public void addMark(int x, int y, String text) {
        this.markList.add(new MapMark(x, y, text));
        postInvalidate();
    }

    public void addMark(float fx, float fy, String text) {
        int x = (int) (fx * mBitmap.getWidth());
        int y = (int) (fy * mBitmap.getHeight());
        this.markList.add(new MapMark(x, y, text));
        postInvalidate();
    }

    class MapMark {
        int x;
        int y;
        String text;

        public MapMark(int x, int y, String text) {
            this.x = x;
            this.y = y;
            this.text = text;
        }
    }
}
