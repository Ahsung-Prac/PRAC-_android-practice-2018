package com.example.prject1;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AttackMotion {
    Bitmap at0;
    Bitmap at1;
    Bitmap at2;
    Bitmap at3;
    static int step;
    static boolean skillon;
    //int cri_x,cri_y;
    int x,y;
    static Timer timer;
    static ArrayList<TimerTask> timerTask;
    public AttackMotion(){
        this.step=0;
        this.skillon=false;
        timer = new Timer();
        timerTask = new ArrayList<TimerTask>();
    }
    /*public void motionSpeed(int a){
        timer.schedule(timerTask,0,a);
    }*/
    static public void productTime(){
        TimerTask temptime = new TimerTask() {
            @Override
            public void run() {
                 step++;
            }
        };
        timerTask.add(temptime);
    }
    public Bitmap currentStep(int cri_x,int cri_y,int a){
        if(step==1){//this.step++;
        x=cri_x+a; y=cri_y-200; return at0;}
        else if(step==2) { //this.step++;
        x=cri_x+a;y=cri_y-200; return at1;}
        else if(step==3){//this.step++;
        x=cri_x+2*a; y=cri_y-500; return at2;}
        else if(step ==4){//this.step=0;
        x=cri_x+2*a; y=cri_y-500; return at3;}
        else if(step ==5){//this.step=0;
            x=cri_x+2*a; y=cri_y-500; step=0;timerTask.get(0).cancel(); timerTask.remove(0); skillon=false; return at3;}
        else return at0;
    }
}
