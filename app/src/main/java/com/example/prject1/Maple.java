package com.example.prject1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Maple extends AppCompatActivity {
    int width;
    int height;
    Charactor ch;
    int chaWidth;
    int chaHeight;
    int cha_x;
    int cha_y;
    Bitmap leftKey,rightKey;
    int buttonSize;
    int leftKey_x,leftKey_y;
    int rightKey_x,rightKey_y;
    AttackMotion rskill;
    int button_x,button_y;
    Bitmap manu;
    Bitmap attack;
    int attak_x;
    AttackMotion lskill;
    boolean threadrun;
    MediaPlayer backMusic;
    SoundPool sp;
    int att;
    private  View decorView; private int uiOption;
    Bitmap screen;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ch = new Charactor();
        rskill = new AttackMotion();
        lskill = new AttackMotion();
        width = (int)size.x;
        height = (int)size.y;
        chaWidth = width/9;
        chaHeight = height/6;
        cha_x = width/5;
        cha_y = height*13/20;
        ch.right = BitmapFactory.decodeResource(getResources(),R.drawable.sikright);
        ch.right = Bitmap.createScaledBitmap(ch.right,chaWidth,chaHeight,true);
        ch.left = BitmapFactory.decodeResource(getResources(),R.drawable.sikleft);
        ch.left = Bitmap.createScaledBitmap(ch.left,chaWidth,chaHeight,true);
        rskill.at0 = BitmapFactory.decodeResource(getResources(),R.drawable.atright0);
        rskill.at0 = Bitmap.createScaledBitmap(rskill.at0,chaWidth*2,chaHeight*2,true);
        rskill.at1 = BitmapFactory.decodeResource(getResources(),R.drawable.atright);
        rskill.at1 = Bitmap.createScaledBitmap(rskill.at1,chaWidth*2,chaHeight*2,true);
        rskill.at2 = BitmapFactory.decodeResource(getResources(),R.drawable.atright2);
        rskill.at2 = Bitmap.createScaledBitmap(rskill.at2,chaWidth*3,chaHeight*4,true);
        rskill.at3 = BitmapFactory.decodeResource(getResources(),R.drawable.atright3);
        rskill.at3 = Bitmap.createScaledBitmap(rskill.at3,chaWidth*3,chaHeight*3,true);
        lskill.at0 = BitmapFactory.decodeResource(getResources(),R.drawable.atleft0);
        lskill.at0 = Bitmap.createScaledBitmap(lskill.at0,chaWidth*2,chaHeight*2,true);
        lskill.at1 = BitmapFactory.decodeResource(getResources(),R.drawable.atleft);
        lskill.at1 = Bitmap.createScaledBitmap(lskill.at1,chaWidth*2,chaHeight*2,true);
        lskill.at2 = BitmapFactory.decodeResource(getResources(),R.drawable.atleft2);
        lskill.at2 = Bitmap.createScaledBitmap(lskill.at2,chaWidth*3,chaHeight*4,true);
        lskill.at3 = BitmapFactory.decodeResource(getResources(),R.drawable.atleft3);
        lskill.at3 = Bitmap.createScaledBitmap(lskill.at3,chaWidth*3,chaHeight*3,true);
        //rskill.cri_x = cha_x;
        //rskill.cri_y=cha_y;
        buttonSize = width/10;
        leftKey_x = width*7/9; leftKey_y=height*2/3-30;
        rightKey_x = width*8/9; rightKey_y=height*2/3-30;
        attak_x = width/9;
        leftKey = BitmapFactory.decodeResource(getResources(),R.drawable.leftkey);
        rightKey = BitmapFactory.decodeResource(getResources(),R.drawable.rightkey);
        leftKey=Bitmap.createScaledBitmap(leftKey,buttonSize,buttonSize,true);
        rightKey=Bitmap.createScaledBitmap(rightKey,buttonSize,buttonSize,true);
        manu = BitmapFactory.decodeResource(getResources(),R.drawable.back);
        manu = Bitmap.createScaledBitmap(manu,buttonSize/2,buttonSize/2,true);
        attack = BitmapFactory.decodeResource(getResources(),R.drawable.missilebutton);
        attack = Bitmap.createScaledBitmap(attack,buttonSize,buttonSize,true);
        screen = BitmapFactory.decodeResource(getResources(),R.drawable.erev);
        screen = Bitmap.createScaledBitmap(screen,width*11/9,height*7/8,true);
        button_x = 50;
        button_y = 70;
        threadrun=true;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MapleView mapleView = new MapleView(this);
        backMusic= MediaPlayer.create(this,R.raw.maplesound);
        backMusic.setLooping(true);
        backMusic.setVolume(0.7f,0.7f);
        backMusic.start();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sp = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(1).build();
        att = sp.load(this,R.raw.attacksound,1);
        setContentView(mapleView);
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
         			uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
        			uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

   	decorView.setSystemUiVisibility( uiOption ); //풀스크린 도구창 홈버튼 숨기기..

    }
    @SuppressLint("ViewConstructor")
    public class MapleView extends SurfaceView implements SurfaceHolder.Callback {
        MyThread mThread;
        SurfaceHolder mHolder;
        Context mContext;
        int moveStaus=0;
        int dirStaus=1;
        int chadir=1;
        public MapleView(Context context){
            super(context);
            SurfaceHolder holder =getHolder();
            holder.addCallback(this);
            mThread = new MyThread(holder,context);
            setFocusable(true);
            //rskill.motionSpeed(90);
            //Handler.sendEmptyMessageDelayed(0,1000);
        }
        public void surfaceCreated(SurfaceHolder holder){
            mThread.start();
        }
        public void surfaceChanged(SurfaceHolder arg0,int format,int width,int height){

        }
        public void surfaceDestroyed(SurfaceHolder holder){
            boolean retry = true;
            while(retry){
                try {
                    mThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                   // e.printStackTrace();
                }
            }
        }

        class MyThread extends Thread {
            int x = 250;
            public MyThread(SurfaceHolder holder, Context context) {
                mHolder = holder;
                mContext = context;
            }
            public  void run(){
                while(threadrun){
                    Canvas canvas=null;
                   canvas = mHolder.lockCanvas();
                   try {
                       synchronized (mHolder) {
                           if(moveStaus ==1 && cha_x<width){cha_x+=20;}
                           else if (moveStaus == -1 && cha_x >0){cha_x-=20;}
                           draw(canvas);
                       }
                   }finally {
                       if (canvas != null) {
                           mHolder.unlockCanvasAndPost(canvas);
                       }
                   }
               }
            }
            public void draw(Canvas canvas) {
                Paint p1 = new Paint();
                p1.setColor(Color.WHITE);
                canvas.drawRect(0, 0, width + 300, height, p1);
                //p1.setTextSize(50);
                canvas.drawBitmap(screen,0,0,p1);
                canvas.drawBitmap(rightKey, rightKey_x, rightKey_y, p1);
                canvas.drawBitmap(leftKey, leftKey_x, leftKey_y, p1);
                canvas.drawBitmap(attack, attak_x, leftKey_y, p1);
                if (dirStaus == 1) {
                    canvas.drawBitmap(ch.right, cha_x, cha_y, p1);
                } else if (dirStaus == -1) {
                    canvas.drawBitmap(ch.left, cha_x, cha_y, p1);
                }
                canvas.drawBitmap(manu, button_x, button_y, p1);
                if (AttackMotion.skillon && chadir == 1) {
                    canvas.drawBitmap(rskill.currentStep(cha_x, cha_y,80), rskill.x, rskill.y, p1);
                }
                else if (AttackMotion.skillon && chadir == -1) {
                    canvas.drawBitmap(lskill.currentStep(cha_x, cha_y,-370), lskill.x, lskill.y, p1);
                }
                //else if(rskill.skillon==false){
                  // rskill.timer.purge();
                //}
            }
        }
        /*Handler Handler = new Handler(){
            public void handleMessage(Message msg){
                invalidate();
                Handler.sendEmptyMessageDelayed(0,30);
            }
        };*/
        public boolean onTouchEvent(MotionEvent event){
            int x=0,y=0;
            if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
                x = (int)event.getX();
                y = (int)event.getY();
            }
            if(event.getAction()==MotionEvent.ACTION_DOWN && AttackMotion.skillon==false)
                if((x>attak_x)&&(x<attak_x+buttonSize)&&(y>leftKey_y)&&(y<leftKey_y+buttonSize))
                {   AttackMotion.skillon=true;
                    AttackMotion.productTime();
                    AttackMotion.timer.schedule(AttackMotion.timerTask.get(0), 0, 90);
                    sp.play(att,1,1,0,0,0);}
            if(event.getAction()==MotionEvent.ACTION_UP){
                moveStaus = 0;
            }
            if((x>leftKey_x)&&(x<leftKey_x+buttonSize)&&(y>leftKey_y)&&(y<leftKey_y+buttonSize) && AttackMotion.skillon==false)
            {dirStaus=-1;moveStaus =-1; chadir=-1;}

            else if((x>rightKey_x)&&(x<rightKey_x+buttonSize)&&(y>leftKey_y)&&(y<leftKey_y+buttonSize)&&AttackMotion.skillon==false)
            {dirStaus=1; moveStaus = 1; chadir = 1;}

            //if(event.getAction() == MotionEvent.ACTION_DOWN)
            if((x>button_x)&&(x<button_x+buttonSize/2)&&(y>button_y)&&(y<button_y+buttonSize/2)){
                threadrun = false;
                backMusic.release();
                sp.release();
                finish();
            }

            // invalidate();
            return true;
        }

    }
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        if(keyCode == KeyEvent.KEYCODE_MOVE_END){
            threadrun = false;
            backMusic.release();
            sp.release();
            finish();
        }
        if(keyCode == KeyEvent.KEYCODE_BACK){
            threadrun = false;
            backMusic.release();
            sp.release();
            finish();
        }
        return false;
    }
    public void onPause() {
        threadrun = false;
        backMusic.release();
        sp.release();
        finish();
        super.onPause();
    }
}
