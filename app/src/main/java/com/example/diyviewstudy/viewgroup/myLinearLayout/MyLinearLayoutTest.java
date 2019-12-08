package com.example.diyviewstudy.viewgroup.myLinearLayout;

import android.os.Bundle;

import com.example.diyviewstudy.R;

import androidx.appcompat.app.AppCompatActivity;

public class MyLinearLayoutTest extends AppCompatActivity {

    private MyLinearLayout myLinearLayout1;
    private MyLinearLayout myLinearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_linear_layout_test);
        myLinearLayout1 = findViewById(R.id.myLinearLayout1);
        myLinearLayout2 = findViewById(R.id.myLinearLayout2);

        myLinearLayout1.setOrientation(1);
        myLinearLayout2.setOrientation(0);
    }
}
