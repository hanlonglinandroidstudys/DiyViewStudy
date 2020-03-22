package com.example.diyviewstudy.view.colorTrackView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diyviewstudy.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * create by DragonForest at 2020/3/23
 */
public class MyFragment extends Fragment {
    int layoutId;

    public MyFragment(int laytoutId) {
        this.layoutId = laytoutId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(layoutId, container, false);
        return v;
    }
}
