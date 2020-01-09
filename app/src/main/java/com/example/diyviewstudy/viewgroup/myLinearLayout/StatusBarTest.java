package com.example.diyviewstudy.viewgroup.myLinearLayout;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.diyviewstudy.R;
import com.example.diyviewstudy.util.StatusBarUtil;
import com.github.zackratos.ultimatebar.UltimateBar;

public class StatusBarTest extends AppCompatActivity {
    Toolbar toolbar;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);

        ll_content = findViewById(R.id.ll_content);
        toolbar = findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        StatusBarUtil.getInstance().setTopPaddingView(ll_content);

        // 半透明
//        UltimateBar.Companion.with(this)
//                .statusDark(false)                  // 状态栏灰色模式(Android 6.0+)，默认 flase
////                .statusDrawable(drawable)           // 状态栏背景，默认 null
////                .statusDrawable2(drawable2)         // Android 6.0 以下状态栏灰色模式时状态栏颜色
//                .applyNavigation(true)              // 应用到导航栏，默认 flase
//                .navigationDark(false)              // 导航栏灰色模式(Android 8.0+)，默认 false
////                .navigationDrawable(drawable)       // 导航栏背景，默认 null
////                .navigationDrawable2(drawable2)     // Android 8.0 以下导航栏灰色模式时导航栏颜色
//                .create()
//                .transparentBar();

        // 沉浸式
//        UltimateBar.Companion.with(this)
//                .statusDark(false)                  // 状态栏灰色模式(Android 6.0+)，默认 flase
////                .statusDrawable2(drawable2)         // Android 6.0 以下状态栏灰色模式时状态栏颜色
//                .applyNavigation(true)              // 应用到导航栏，默认 flase
//                .navigationDark(false)              // 导航栏灰色模式(Android 8.0+)，默认 false
////                .navigationDrawable2(drawable2)     // Android 8.0 以下导航栏灰色模式时导航栏颜色
//                .create()
//                .immersionBar();

//        StatusBarUtil.setTranslucent(this);
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            UltimateBar.Companion.with(this)
//                    .applyNavigation(true)      // 是否应用到导航栏
//                    .create()
//                    .hideBar();
//        }
//    }
}
