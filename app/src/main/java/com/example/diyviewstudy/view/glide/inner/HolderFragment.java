package com.example.diyviewstudy.view.glide.inner;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 监控activity生命周期
 * create by DragonForest at 2020/4/11
 */
public class HolderFragment extends Fragment {
    String TAG = "HolderFragment";
    List<ILifeCircleObserver> observers = new ArrayList<>();

    public void addObserver(ILifeCircleObserver observer){
        observers.add(observer);
    }

    public void removeObserver(ILifeCircleObserver observer){
        observers.remove(observer);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
        // 触发监听
        dispatchStart2Observers();
    }

    private void dispatchStart2Observers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
        dispatchStop2Observers();
    }

    private void dispatchStop2Observers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onStop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }
}
