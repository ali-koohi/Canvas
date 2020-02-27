package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout lin=findViewById(R.id.lin);

        final CustomArcView c=new CustomArcView(this);
        lin.addView(c);
        c.setPrices(new int[]{165,300,165,210,60},new int[]{Color.GREEN,Color.RED,Color.BLUE,Color.YELLOW,Color.CYAN});

    }
}
