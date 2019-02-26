package com.example.prject1;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Database extends AppCompatActivity {
    private static final String DATABASE_NAME = "InnerDatabase(SQLIte).db";
    private static final int DATABASE_VERSION = 1;
    public  static SQLiteDatabase mDB;
    private MyDbHelper m_helper;
    private Context mContext;
    //ArrayAdapter<String> arrayAdapter;
    Button DBbutton;
    Button dataButton;
    Button loadButton;
    Button delete_Button;
    ListView conList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
       // arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        mContext = Database.this;//context 따라가서 검사해보고,...
        DBbutton = (Button)findViewById(R.id.button19);
        DBbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText editText = new EditText(Database.this);
                editText.setHint("DB이름을 입력");
                AlertDialog.Builder dialog = new AlertDialog.Builder(Database.this);
                dialog.setTitle("Database 설정")
                        .setMessage("DB 이름을 입력하세요")
                        .setView(editText)
                        .setPositiveButton("생성", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(editText.getText().toString().length()>0){
                                    m_helper = new MyDbHelper(mContext,editText.getText().toString(),null,1);
                                    m_helper.testDB();
                                    m_helper.existedDB(editText.getText().toString());
                                }
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        });
        dataButton = (Button)findViewById(R.id.datacreatButton);
        dataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout layout = new LinearLayout(mContext);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText editTitle = new EditText(mContext);
                editTitle.setHint("제목 입력");
                editTitle.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                editTitle.setInputType(InputType.TYPE_CLASS_TEXT);
                final EditText editName = new EditText(mContext);
                editName.setHint("이름 입력");
                editName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                editName.setInputType(InputType.TYPE_CLASS_TEXT);
                final EditText editAge = new EditText(mContext);
                editAge.setHint("나이 입력");
                editAge.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                editAge.setInputType(InputType.TYPE_CLASS_TEXT);
                final EditText editContents = new EditText(mContext);
                editContents.setHint("내용 입력");

                layout.addView(editTitle);
                layout.addView(editName);
                layout.addView(editAge);
                layout.addView(editContents);

                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("메모 입력")
                        .setView(layout)
                        .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String title = editTitle.getText().toString();
                                String name = editName.getText().toString();
                                String age = editAge.getText().toString();
                                String contents = editContents.getText().toString();
                                if( m_helper == null){
                                    m_helper = new MyDbHelper(mContext,"TEST_TABLE",null,1);
                                }
                                MemoC memoC = new MemoC();
                                memoC.setTilte(title);
                                memoC.setName(name);
                                memoC.setAge(age);
                                memoC.setContents(contents);
                                m_helper.addMemo(memoC);
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        });
        delete_Button = (Button)findViewById(R.id.deleteButton);
        delete_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText editText = new EditText(Database.this);
                editText.setHint("삭제할 번호 입력");
                AlertDialog.Builder dialog = new AlertDialog.Builder(Database.this);
                dialog.setTitle("메모 삭제")
                        .setMessage("삭제할 번호를 입력하세요")
                        .setView(editText)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(m_helper == null){
                                    Toast.makeText(Database.this, "선택된 DB가 없습니다.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    m_helper.deleteMemo(Integer.parseInt(editText.getText().toString()));
                                    List memos = m_helper.getAllMemoData();
                                    conList.setAdapter(new MemoListAdapter(memos,mContext));
                                }
                            }
                        }).setNegativeButton("전체 삭제", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 if(m_helper == null){
                                     Toast.makeText(Database.this, "선택된 DB가 없습니다.", Toast.LENGTH_SHORT).show();
                                 }
                                 else{
                                     m_helper.allDeleteMemo();
                                     List memos = m_helper.getAllMemoData();
                                     conList.setAdapter(new MemoListAdapter(memos,mContext));
                                 }
                             }
                         })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        });
        conList = (ListView)findViewById(R.id.list1);
        loadButton = (Button)findViewById(R.id.dataLoadButton);
        loadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                conList.setVisibility(View.VISIBLE);
                if(m_helper == null){
                    m_helper = new MyDbHelper(mContext,"TEST_TABLE",null,1);
                }
                List memos = m_helper.getAllMemoData();
                conList.setAdapter(new MemoListAdapter(memos,mContext));
            }
        });

    }
protected void onDestroy() {
        if(m_helper !=null) {
            m_helper.close();
        }
    super.onDestroy();
}
}
