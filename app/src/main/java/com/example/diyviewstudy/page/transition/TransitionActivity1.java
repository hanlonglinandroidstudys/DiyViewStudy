package com.example.diyviewstudy.page.transition;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.diyviewstudy.R;

public class TransitionActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAc2();
            }
        });
    }

    private void goAc2() {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, findViewById(R.id.img_map), "mapImage");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(TransitionActivity1.this,TransitionActivity2.class),options.toBundle());
        }
    }
}
