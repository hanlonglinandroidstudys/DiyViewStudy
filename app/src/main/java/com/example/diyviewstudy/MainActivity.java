package com.example.diyviewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.diyviewstudy.view.CanvasView.CanvasTest;
import com.example.diyviewstudy.view.clearEditView.ClearEditTest;
import com.example.diyviewstudy.view.clearEditView.ClearableEditText;
import com.example.diyviewstudy.view.gradientView.GradientTest;
import com.example.diyviewstudy.view.headView.HeadViewTest;
import com.example.diyviewstudy.view.magnifyingView.MagnifyingTest;
import com.example.diyviewstudy.view.shadowLayer.ShadowTest;
import com.example.diyviewstudy.view.telescopeView.TeleScopeViewTest;
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
            case R.id.btn4:
                go(ShadowTest.class);
                break;
            case R.id.btn5:
                go(ClearEditTest.class);
                break;
            case R.id.btn6:
                go(TeleScopeViewTest.class);
                break;
            case R.id.btn7:
                go(HeadViewTest.class);
                break;
            case R.id.btn8:
                go(GradientTest.class);
                break;
            case R.id.btn9:
                go(MagnifyingTest.class);
                break;
        }
    }

    private void go(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }
}
