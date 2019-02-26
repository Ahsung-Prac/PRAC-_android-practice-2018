package com.example.prject1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Diary extends AppCompatActivity {
    TextView textView,textView2;
    EditText ed;
    Button button1,button3;
    String s;
    String temp;
    FileTable f;
    Button button2;
    //MyDbHelper m_helper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        f = new FileTable();
        ed = (EditText)findViewById(R.id.ed1);
        textView2= (TextView)findViewById(R.id.textView2);
        textView= (TextView)findViewById(R.id.textView1);
        //textView.setText("앙뇽");
        //textView.setText("뜨억!");
        button1 =(Button)findViewById(R.id.button12);
        button2 = (Button)findViewById(R.id.button13);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               /* f.loadFile(0);
                temp = f.diary1;
                textView.setText(temp);*/
               try{
                StringBuffer data = new StringBuffer();
                FileInputStream fis = openFileInput("sss.txt");//파일명
                BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
                String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
                while (str != null) {
                    data.append(str+"\n");
                        str = buffer.readLine();
                }
                textView.setText(data);
                buffer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                s = ed.getText().toString();
                try { FileOutputStream op = openFileOutput("sss.txt",Context.MODE_APPEND);
               PrintWriter out = new PrintWriter(op);
               if(s!="") {
                   out.println(s);
               }
                   out.close();
                    /*op.write((char)'a');
                    op.write((char)'\0');
                    op.close();*/
                    ed.setText("");
                }catch (IOException e){e.printStackTrace();}
                textView2.setText(s);
            }
        });
        button3=(Button)findViewById(R.id.button14);
        button3.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                s = ed.getText().toString();
                try { FileOutputStream op = openFileOutput("sss.txt",Context.MODE_PRIVATE);
                    PrintWriter out = new PrintWriter(op);
                    out.println(s);
                    out.close();
                    /*op.write((char)'a');
                    op.write((char)'\0');
                    op.close();*/
                    ed.setText("");
                }catch (IOException e){e.printStackTrace();}
                textView2.setText(s);
            }
        });
        //textView.setText(f.diary1.diary);
        //m_helper = new MyDbHelper(this,"test.db",null,1);
        //SQLiteDatabase db = m_helper.getReadableDatabase();
        //String sql = String.format("INSERT INTO DailyDiary VALUES(NULL,'%s','%s');"
    }
   /* public void buttonTransfer(View view){
        textView.setText(ed.getText().toString());
    }*/
    public class FileTable{
        InputStream fi;
        OutputStream fo;
        BufferedWriter bw;
        BufferedReader br;
        String diary1;
        FileWriter fw;
        public void loadFile(int num){
            fi = Diary.this.getResources().openRawResource(R.raw.diary+num);
            try{
                byte[] data = new byte[fi.available()];
                fi.read(data);
                fi.close();
                diary1 = new String(data,"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void storeFile(String s) throws IOException{
            try { FileOutputStream op = openFileOutput(getFilesDir()+"ttt.txt",Context.MODE_APPEND);
              /* PrintWriter out = new PrintWriter(op);
               out.println(s);
               out.close();*/
               op.write((char)'a');
               op.write((char)'\0');
               op.close();
            }catch (IOException e){e.printStackTrace();}
        }
    }
    /*public class FileSplit0{
        String diary;
        public FileSplit0(String str){
            diary = str;
        }
    }*/
    public void buttonListenerDB(View v){
        Intent it = new Intent(getApplicationContext(),Database.class);
        startActivity(it);
    }
    //update git!
}
