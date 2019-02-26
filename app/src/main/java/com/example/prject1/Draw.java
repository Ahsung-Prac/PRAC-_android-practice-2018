package com.example.prject1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Draw extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
    }
    public void setRed(View view){
        MyView.whatColor=1;
    }
    public void setBlue(View view){
        MyView.whatColor=2;
    }
    public void setYellow(View view){
        MyView.whatColor=3;
    }
    public void setGreen(View view){
        MyView.whatColor=4;
    }
    public void setBlack(View view){
        MyView.whatColor=0;
    }
}

