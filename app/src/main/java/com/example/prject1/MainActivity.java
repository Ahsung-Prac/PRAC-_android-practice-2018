package com.example.prject1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    Button b3;
    String str;
    EditText ee1;
    TextView tt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout m =(LinearLayout) findViewById(R.id.lay);
        //m.setBackgroundColor(Color.MAGENT);
       ee1 = (EditText) findViewById(R.id.e1);
        b3 = (Button) findViewById(R.id.button3);
        tt1 = (TextView)findViewById(R.id.t1);
        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent1 = new Intent(getApplicationContext(),ShowMe.class);
                startActivity(intent1);
            }
        });

       /* Button.OnClickListener myClick = new Button.OnClickListener(){
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.button1:
                        Toast.makeText(getApplicationContext(), "나 사랑해?", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.button2:
                        setContentView(R.layout.activity_page2);
                }
            }
        };
        findViewById(R.id.button1).setOnClickListener(myClick);
        findViewById(R.id.button2).setOnClickListener(myClick);
        MyListner2 mL2 = new MyListner2();
        b1 = (Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"나 사랑해?",Toast.LENGTH_SHORT).show();
            }
        });
        b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(mL2);*/
    }
    public void buttonListener1(View v){
        Toast.makeText(getApplicationContext(), "사랑해?", Toast.LENGTH_SHORT).show();
    }
    public void buttonListener2(View v){
        Intent it = new Intent(getApplicationContext(),Draw.class);
        startActivity(it);
    }
    public void buttonListener3(View v){
        Intent it = new Intent(getApplicationContext(),Move.class);
        startActivity(it);
    }
    public void buttonListener4(View v){
        Intent it = new Intent(getApplicationContext(),Maple.class);
        startActivity(it);
    }
    public void buttonListener5(View v){
        Intent it = new Intent(getApplicationContext(),Diary.class);
        startActivity(it);
    }
    /*class MyListner2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
             setContentView(R.layout.activity_page2);
        }
    }*/
    public boolean onTouchEvent(MotionEvent event) {
      if(event.getAction() == MotionEvent.ACTION_UP){
          Toast.makeText(getApplicationContext(), "무브무브", Toast.LENGTH_SHORT).show();
      }

        return false;
    }


}
