package com.example.diyviewstudy.view.refreshView;

import android.view.View;

public interface IHeaderView {

    void inflate(int layoutId);

    View getView();

    /**
     * 开始下拉到 有效距离之前
     * @param fac 开始到有效距离的比例
     */
    void onStart(float fac);

    /**
     * 有效距离到最大下拉距离之前
     * @param fac 有效距离大最大距离的比例
     */
    void onEffect(float fac);

    /**
     * 正在加载
     */
    void onLoading();

    /**
     * 结束刷新
     */
    void onFinish();
}
