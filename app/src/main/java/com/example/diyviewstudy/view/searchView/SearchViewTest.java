package com.example.diyviewstudy.view.searchView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.diyviewstudy.R;

public class SearchViewTest extends AppCompatActivity {

    private SearchView searchView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_test);
        searchView1 = findViewById(R.id.searchView1);
        searchView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchView1.getState()==SearchView.STATE_NONE){
                    searchView1.start();
                }else if(searchView1.getState()==SearchView.STATE_LOADING){
                    searchView1.stop();
                }
            }
        });
    }
}
