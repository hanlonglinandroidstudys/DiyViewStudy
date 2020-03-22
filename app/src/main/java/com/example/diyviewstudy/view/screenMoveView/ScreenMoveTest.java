package com.example.diyviewstudy.view.screenMoveView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.diyviewstudy.R;

public class ScreenMoveTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_move_test);
        ScreenMoveView2 screenMoveView2 = new ScreenMoveView2(this);
        screenMoveView2.attachToWindow();
    }
}
