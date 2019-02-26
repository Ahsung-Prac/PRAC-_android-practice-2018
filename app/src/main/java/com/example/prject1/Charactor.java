package com.example.prject1;

import android.graphics.Bitmap;
import android.os.Bundle;

public class Charactor {
    Bitmap right;
    Bitmap left;
    int status;
    public Charactor(){
        this.status = 0;
    }
    public void setStatus(int a){
        status =a;
    }
}
