package com.example.diyviewstudy.view.dymapView;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.diyviewstudy.R;

public class DyMapViewTest extends AppCompatActivity {

    private DyMapView dyMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dy_map_view_test);

        dyMapView = findViewById(R.id.dyMapView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map);
        dyMapView.setBitmap(bitmap);

//        dyMapView.addMark(50,60,"");
//        dyMapView.addMark(500,60,"");
//        dyMapView.addMark(1000,200,"");
//        dyMapView.addMark(1500,300,"");

        dyMapView.addMark(0.2f,0.3f,"");
        dyMapView.addMark(0.4f,0.3f,"");
        dyMapView.addMark(0.5f,0.3f,"");
        dyMapView.addMark(0.8f,0.3f,"");
    }
}
