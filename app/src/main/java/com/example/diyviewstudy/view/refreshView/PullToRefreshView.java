package com.example.diyviewstudy.view.refreshView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.example.diyviewstudy.R;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class PullToRefreshView extends ViewGroup {
    private IHeaderView headerView;
    private float lastY;

    /** 生效距离*/
    float effectLength=300;
    /** 最大距离*/
    float maxLength=600;
    /**  回弹时间*/
    int scrollDuration=200;

    Scroller mScroller;

    public PullToRefreshView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(); }

    private void init() {
        mScroller=new Scroller(getContext());
//        headerView = new DefaultHeaderView(getContext());
        headerView=new MyHeaderView(getContext());
        addView(headerView.getView());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        Log.e(TAG,"childCount:"+childCount);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child == headerView.getView()) {
                // 头布局 摆放在屏幕上面
                Log.e(TAG,"找到头布局"+child.getMeasuredHeight());
                child.layout(0, -child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
            } else {
                // 普通布局 摆放在屏幕中
                bottom = top + child.getMeasuredHeight();
                right = left + child.getMeasuredWidth();
                child.layout(left, top, right, bottom);

                top = bottom;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy=lastY-event.getY();
                if(Math.abs(getScrollY())<maxLength){
                    scrollBy(0,(int)dy);
                    if(Math.abs(getScrollY())<effectLength){
                        headerView.onStart(Math.abs(getScrollY()/effectLength));
                    }else{
                        headerView.onEffect((Math.abs(getScrollY())-effectLength)/(maxLength-effectLength));
                    }
                }
                Log.e(TAG,"getScrollY-->"+getScrollY()+",getY--->"+getY()+",getTranslationY--->"+getTranslationY());
                lastY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if(Math.abs(getScrollY())<effectLength){
                    mScroller.startScroll(0,getScrollY(),0,-getScrollY(),scrollDuration);
                    invalidate();
                    headerView.onFinish();
                }else{
                    mScroller.startScroll(0,getScrollY(),0,-(int)effectLength-getScrollY(),scrollDuration);
                    invalidate();
                    headerView.onLoading();
                }
                break;

        }
        return true;
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }


    public void setHeaderView(IHeaderView headerView) {
        if(this.headerView!=null){
            removeView(headerView.getView());
        }
        this.headerView = headerView;
        addView(headerView.getView());
    }
}
