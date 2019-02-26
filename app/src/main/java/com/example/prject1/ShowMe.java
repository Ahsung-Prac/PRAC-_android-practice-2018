package com.example.prject1;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowMe extends AppCompatActivity {
    Button b3;
    String str;
    EditText ee1;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        b3 = (Button)findViewById(R.id.button3);
        ee1 = (EditText)findViewById(R.id.e1);
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                str = ee1.getText().toString();
                if(str.equals("20180707")) {
                    Toast.makeText(getApplicationContext(), "쩡답!!", Toast.LENGTH_SHORT).show();
                }
                else {Toast.makeText(getApplicationContext(),"틀렸다 입력한 문자: "+ str, Toast.LENGTH_SHORT).show();}
            }
        });
    }
    public  void onButtonClickTEll(View v){
        Intent it = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-4137-2301"));
        startActivity(it);
    }
}
