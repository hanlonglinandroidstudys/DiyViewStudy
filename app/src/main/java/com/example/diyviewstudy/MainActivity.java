package com.example.diyviewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.diyviewstudy.event.InterceptEventTest;
import com.example.diyviewstudy.page.headscroll.WechatHeaderTest;
import com.example.diyviewstudy.page.transition.TransitionActivity1;
import com.example.diyviewstudy.setFactory2.SetFactory2Test;
import com.example.diyviewstudy.view.canvasView.CanvasTest;
import com.example.diyviewstudy.view.clearEditView.ClearEditTest;
import com.example.diyviewstudy.view.dropdownView.DropDownTest;
import com.example.diyviewstudy.view.dymapView.DyMapViewTest;
import com.example.diyviewstudy.view.gestureView.GestureViewTest;
import com.example.diyviewstudy.view.gradientView.GradientTest;
import com.example.diyviewstudy.view.headView.HeadViewTest;
import com.example.diyviewstudy.view.magnifyingView.MagnifyingTest;
import com.example.diyviewstudy.view.refreshView.PullToRefreshTest;
import com.example.diyviewstudy.view.screenMoveView.ScreenMoveTest;
import com.example.diyviewstudy.view.searchView.SearchViewTest;
import com.example.diyviewstudy.view.shadowLayer.ShadowTest;
import com.example.diyviewstudy.view.telescopeView.TeleScopeViewTest;
import com.example.diyviewstudy.view.textView.DxTextViewTest;
import com.example.diyviewstudy.view.waveView.WaveTest;
import com.example.diyviewstudy.viewgroup.flowLayout.FlowlayoutTest;
import com.example.diyviewstudy.viewgroup.myLinearLayout.MyLinearLayoutTest;
import com.example.diyviewstudy.viewgroup.myLinearLayout.StatusBarTest;

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
            case R.id.btn10:
                go(FlowlayoutTest.class);
                break;
            case R.id.btn11:
                go(SearchViewTest.class);
                break;
            case R.id.btn12:
                go(PullToRefreshTest.class);
                break;
            case R.id.btn13:
                go(ScreenMoveTest.class);
                break;
            case R.id.btn14:
                go(GestureViewTest.class);
                break;
            case R.id.btn15:
                go(DyMapViewTest.class);
                break;
            case R.id.btn16:
                go(StatusBarTest.class);
                break;
            case R.id.btn17:
                go(WechatHeaderTest.class);
                break;
            case R.id.btn18:
                go(TransitionActivity1.class);
                break;
            case R.id.btn19:
                go(DropDownTest.class);
                break;
            case R.id.btn20:
                go(DxTextViewTest.class);
                break;
            case R.id.btn21:
                go(InterceptEventTest.class);
                break;
            case R.id.btn22:
                go(SetFactory2Test.class);
                break;
        }
    }

    private void go(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }
}
