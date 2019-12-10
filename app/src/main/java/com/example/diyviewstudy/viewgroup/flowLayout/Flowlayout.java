package com.example.diyviewstudy.viewgroup.flowLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 *
 * <p>
 * author: DragonForest
 * time: 2019/12/10
 */
public class Flowlayout extends ViewGroup {
    /**
     * 所有行view的集合
     */
    List<List<View>> lineViewsList = new ArrayList<>();
    /**
     * 每行高度的集合
     */
    List<Integer> lineHeightList = new ArrayList<>();

    public Flowlayout(Context context) {
        super(context);
    }

    public Flowlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int totalWidth = widthSize;
        int totalHeight = 0;

        // 行当前的宽度
        int lineWidth = 0;
        // 行的最大高度
        int lineHeight = 0;
        // 行view集合
        List<View> lineViews = new ArrayList<>();
        lineViewsList.clear();
        lineHeightList.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if ((lineWidth + childWidth) <= widthSize) {
                // 未换行
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
                lineViews.add(child);
            } else {
                // 换行
                // 先保存
                lineViewsList.add(lineViews);
                lineHeightList.add(lineHeight);
                totalHeight += lineHeight;
                // 后重置
                lineViews = new ArrayList<>();
                lineHeight = 0;
                lineWidth = 0;
                // 添加
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
                lineViews.add(child);
            }
        }
        // 处理最后一行
        if (lineViews.size() != 0) {
            lineViewsList.add(lineViews);
            lineHeightList.add(lineHeight);
            totalHeight += lineHeight;
        }

        // 设置自身的长宽
        Log.e("FlowLayout", "heightSize:" + heightSize);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                totalHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                totalHeight = Math.min(heightSize, totalHeight);
                break;
        }
        Log.e("FlowLayout", "totalHeight:" + totalHeight);
        setMeasuredDimension(totalWidth, totalHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < lineViewsList.size(); i++) {
            List<View> lineViews = lineViewsList.get(i);
            left = 0;
            for (int i1 = 0; i1 < lineViews.size(); i1++) {
                View child = lineViews.get(i1);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                right = left + childWidth;
                bottom = top + childHeight;
                child.layout(left + lp.leftMargin, top + lp.topMargin, right - lp.rightMargin, bottom - lp.bottomMargin);
                // 右移
                left += childWidth;
            }
            // 下移
            top += lineHeightList.get(i);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }
}
