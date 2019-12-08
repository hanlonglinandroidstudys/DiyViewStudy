package com.example.diyviewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.diyviewstudy.view.CanvasView.CanvasTest;
import com.example.diyviewstudy.view.waveview.WaveTest;
import com.example.diyviewstudy.viewgroup.myLinearLayout.MyLinearLayoutTest;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                go(CanvasTest.class);
                break;
            case R.id.btn2:
                go(WaveTest.class);
                break;
            case R.id.btn3:
                go(MyLinearLayoutTest.class);
                break;
        }
    }

    private void go(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }
}
