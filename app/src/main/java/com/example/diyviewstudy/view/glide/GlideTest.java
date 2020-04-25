package com.example.diyviewstudy.view.glide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.diyviewstudy.R;
import com.example.diyviewstudy.page.headscroll.WechatHeaderTest;

import androidx.appcompat.app.AppCompatActivity;

public class GlideTest extends AppCompatActivity {

    LinearLayout ll_content;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        initView();
    }

    private void initView() {
        ll_content=findViewById(R.id.ll_content);
        btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });
        findViewById(R.id.btn_go_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GlideTest.this, WechatHeaderTest.class));
            }
        });
    }

    private void addImage() {
        ImageView iv=new ImageView(this);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(200,200);
        iv.setLayoutParams(lp);
        ll_content.addView(iv);

        Glide.getInstance().with(this).load(R.drawable.ship).into(iv);

    }
}
