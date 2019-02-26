package com.example.prject1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Move extends AppCompatActivity {
    Bitmap spaceship;
    int spaceshipWidth;
    int spaceship_x;
    int spaceship_y;
    Bitmap leftKey;
    int leftKey_x;
    int leftKey_y;
    int rightKey_x;
    int rightKey_y;
    int buttonSize;
    Bitmap rightKey;
    Bitmap screenspace;
    int rightLimit;
    int upLimit;
    Bitmap missileButton;
    int missileButton_x; int missileButton_y;
    int missileButton_width;
    int missile_middle;
    int missileWidth;
    Bitmap missile;
    Bitmap planetimg;
    ArrayList<Missile> myM;
    ArrayList<Planet> planets;
    int score=0;
    int missileNum;
    Timer timer;
    TimerTask timerTask;
    int count = 50;




    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_move);
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        spaceship = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);
        int x = spaceshipWidth= size.x/8;
        int y = size.y/11;
        upLimit =(int)size.y;
        rightLimit = (int)size.x;
        spaceship_x = size.x*1/9;
        spaceship_y = size.y*6/9;
        spaceship = Bitmap.createScaledBitmap(spaceship,x,y,true);
        leftKey = BitmapFactory.decodeResource(getResources(),R.drawable.leftkey);
        rightKey = BitmapFactory.decodeResource(getResources(),R.drawable.rightkey);
        leftKey_x = size.x*5/9;
        leftKey_y = size.y*7/9;
        rightKey_x=size.x*7/9;
        rightKey_y = size.y*7/9;
        buttonSize = size.x/6;
        missileButton_x = size.x/11;
        missileButton_y = size.y*7/9;
        leftKey=Bitmap.createScaledBitmap(leftKey,buttonSize,buttonSize,true);
        rightKey=Bitmap.createScaledBitmap(rightKey,buttonSize,buttonSize,true);
        myM = new ArrayList<Missile>();
        planets= new ArrayList<Planet>();
        missile = BitmapFactory.decodeResource(getResources(),R.drawable.razer);
        missile = Bitmap.createScaledBitmap(missile,buttonSize/4,buttonSize/4,true);
        missileWidth = missile.getWidth();
        missile_middle = missileWidth/2;
        planetimg = BitmapFactory.decodeResource(getResources(),R.drawable.planet);
        planetimg = Bitmap.createScaledBitmap(planetimg,buttonSize*3/5,buttonSize*3/5,true);
        missileButton = BitmapFactory.decodeResource(getResources(),R.drawable.missilebutton);
        missileButton = Bitmap.createScaledBitmap(missileButton,buttonSize,buttonSize,true);
        screenspace = BitmapFactory.decodeResource(getResources(),R.drawable.space);
        screenspace= Bitmap.createScaledBitmap(screenspace,size.x,size.y,true);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(count>0) count--;
            }
        };
        timer.schedule(timerTask,0,1000);
       MoveView mv= new MoveView(this);
        setContentView(mv);
    }
    @SuppressLint("ViewConstructor")
     class MoveView extends View{
        int spaceshipMovement = 0;
        public MoveView(Context context) {
            super(context);
            gHandler.sendEmptyMessageDelayed(0,1000);
        }
        synchronized public void onDraw(Canvas canvas){
            Random r1 = new Random();
            int x = r1.nextInt(rightLimit);
            int d = r1.nextInt(2);
            int y = r1.nextInt(550);
            if(planets.size()<4)
                planets.add(new Planet(x,y+150,d));
            Paint p1 = new Paint();
            missileNum = 2-myM.size();
            canvas.drawBitmap(screenspace,0,0,p1);
            p1.setColor(Color.GREEN);
            p1.setTextSize(70);
            canvas.drawBitmap(spaceship,spaceship_x,spaceship_y,p1);
            canvas.drawBitmap(leftKey,leftKey_x,leftKey_y,p1);
            canvas.drawBitmap(rightKey,rightKey_x,leftKey_y,p1);
            canvas.drawBitmap(missileButton,missileButton_x,missileButton_y,p1);
            for(Missile temp : myM){
                canvas.drawBitmap(missile,temp.x,temp.y,p1);
            }
            for(Planet temp: planets){
                canvas.drawBitmap(planetimg,temp.x,temp.y,p1);
            }
            moveMissile();
            movePlanet();
            checkCollision();
            canvas.drawText("탄알수: "+missileNum,100,200,p1);
            canvas.drawText("점수: "+score,500,200,p1);
            if(count<11){
                p1.setColor(Color.RED);
            }
            canvas.drawText("남은 시간: "+count,1000,200,p1);//count++;
            if(count<1){
                p1.setColor(Color.YELLOW);
                p1.setTextSize(300);
                canvas.drawText("The End",180,upLimit*3/10,p1);
                canvas.drawText("점수:"+score,180,upLimit*4/10,p1);
            }
        }
        public void moveMissile(){
            for(int i=myM.size()-1;i>=0;i--){
                myM.get(i).move();
            }
            for(int i = myM.size()-1; i>=0;i--){
                if(myM.get(i).y<100) myM.remove(i);
            }
        }
        public void movePlanet(){
            for(int i = planets.size()-1;i>=0;i--){
                planets.get(i).move();
            }
            for(int i = planets.size()-1;i>=0;i--){
                if(planets.get(i).x<-buttonSize-10||planets.get(i).x>rightLimit+10) planets.remove(i);
            }
        }
        public void checkCollision(){
            for(int i = planets.size()-1;i>=0;i--){
                for(int j = myM.size()-1;j>=0;j--){
                    if(myM.get(j).x+missile_middle>planets.get(i).x && myM.get(j).x+missile_middle < planets.get(i).x+buttonSize*3/5 && myM.get(j).y>planets.get(i).y && myM.get(j).y< planets.get(i).y + buttonSize*3/5){
                        planets.remove(i);
                        myM.remove(j);
                        if(count>0) score++;
                    }
                }
            }
        }
        Handler gHandler = new Handler(){
            public void handleMessage(Message msg){
                if(spaceshipMovement==1 && spaceship_x < rightLimit-spaceshipWidth+20)
                    spaceship_x+=20;
                else if(spaceshipMovement ==-1 && spaceship_x >0)
                    spaceship_x -= 20;
                invalidate();
                gHandler.sendEmptyMessageDelayed(0,30);
            }
        };

        public boolean onTouchEvent(MotionEvent event){
            int x=0,y=0;
            if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
                x = (int)event.getX();
                y = (int)event.getY();
            }
            if((x>leftKey_x)&&(x<leftKey_x+buttonSize)&&(y>leftKey_y)&&(y<leftKey_y+buttonSize)&&spaceship_x>=0)
                spaceshipMovement= -1;

            else if((x>rightKey_x)&&(x<rightKey_x+buttonSize)&&(y>leftKey_y)&&(y<leftKey_y+buttonSize)&&spaceship_x<=rightLimit*5/6)
                spaceshipMovement=1;
            else if(event.getAction() == MotionEvent.ACTION_UP){
                spaceshipMovement =0;
            }
            if(event.getAction() == MotionEvent.ACTION_DOWN)
                if((x>missileButton_x)&&(x<missileButton_x+buttonSize)&&(y>missileButton_y)&&(y<missileButton_y+buttonSize)){
                if(myM.size()<2){
                    myM.add(new Missile(spaceship_x+spaceshipWidth/2-missile_middle,spaceship_y));
                }
            }
           // invalidate();
            return true;
        }
    }
}