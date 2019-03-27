package com.example.week7day2hw;

import android.app.Activity ;
import android.os.Bundle ;

import com.example.week7day2hw.R ;
import com.example.week7day2hw.Triangle ;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        //direction and color can be set in code as well as in XML attributes
        Triangle triangle = ((Triangle) findViewById(R.id.viewOfTriangle)) ;
        triangle.setDirection(Triangle.Direction.UP) ;
        triangle.setColor(0xFFFFEB3B) ;
    }
}