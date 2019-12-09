package com.example.diyviewstudy.view.gradientView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.diyviewstudy.R;

public class GradientTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_test);

        GradientTextView myGradientTextView = findViewById(R.id.myGradientTextView);
        myGradientTextView.startAnim();
        GradientTextView2 myGradientTextView2 = findViewById(R.id.myGradientTextView2);
        myGradientTextView2.startAnim();
    }
}
