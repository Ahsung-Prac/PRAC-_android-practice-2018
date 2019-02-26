package com.example.prject1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {
    Context context;
    boolean existedTable;
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        this.context = context;
        existedTable = true;
    }
    public  void existedDB(String s){
        if(existedTable){
            Toast.makeText(context, "기존 DB_" +s+"_TABLE 접근.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(MyDbConstru.CreateDB._CREATE0);
        existedTable = false;
        Toast.makeText(context, "New Table 생성", Toast.LENGTH_SHORT).show();
    }
    //"create table if not exists DailyDiary (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+" user TEXT not null, contents TEXT not null);"
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        // db.execSQL("DROP TABLE IF EXISTS person" + MyDbConstru.CreateDB._CREATE0);
        //onCreate(db);
        Toast.makeText(context, "버젼업!", Toast.LENGTH_SHORT).show();
    }
    public void testDB(){
        SQLiteDatabase db = getReadableDatabase();
    }
    public void addMemo(MemoC memoC){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TEST_TABLE ( ");    ///DB 테이블 이름이 가변이 아니고 고정인데 오류 뜨면 확인해
        sb.append(" TITLE, NAME, AGE, CONTENTS ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ) ");  //// ㅇ요요거랑
        db.execSQL(sb.toString(),new Object[]{
                memoC.getTilte(),memoC.getName(),memoC.getAge(),memoC.getContents()
        });;
        Toast.makeText(context, "입력 완료!", Toast.LENGTH_SHORT).show();
    }
    public void deleteMemo(int _id){
        String sb = String.format("DELETE FROM TEST_TABLE WHERE _ID = '%d'",_id);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sb);
    }
    public void allDeleteMemo(){
        String sb = String.format("DELETE FROM TEST_TABLE ");
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sb);
    }
    public List getAllMemoData(){
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, TITLE, NAME, AGE, CONTENTS FROM TEST_TABLE");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString(),null);
        List memos = new ArrayList();
        MemoC memoC = null;
        while(cursor.moveToNext()){
            memoC = new MemoC();
            memoC.set_id(cursor.getInt(0));
            memoC.setTilte(cursor.getString(1));
            memoC.setName(cursor.getString(2));
            memoC.setAge(cursor.getString(3));
            memoC.setContents(cursor.getString(4));
            memos.add(memoC);
        }
        return memos;
    }

    /*public void open() throws SQLException {
        m_helper = new MyDbHelper(mContext,DATABASE_NAME,null,DATABASE_VERSION);
        mDB = m_helper.getWritableDatabase();
    }
    public void create(){
        m_helper.onCreate(mDB);
    }
    public void close(){
        mDB.close();
    }
    public long insertColumn(String userid,String name,long age,String contexts){
        ContentValues values = new ContentValues();
        values.put(MyDbConstru.CreateDB.USERID,userid);
        values.put(MyDbConstru.CreateDB.NAME,name);
        values.put(MyDbConstru.CreateDB.AGE,age);
        values.put(MyDbConstru.CreateDB.CONTEXTS,contexts);
        return mDB.insert(MyDbConstru.CreateDB._TABLENAME0,null,values);
    }
    public Cursor selectColumns(){
        return mDB.query(MyDbConstru.CreateDB._TABLENAME0,null,null,null,null,null,null);
    }
    public boolean updataColumn(String userid,String name,long age,String contexts){
        ContentValues values = new ContentValues();
        values.put(MyDbConstru.CreateDB.USERID,userid);
        values.put(MyDbConstru.CreateDB.NAME,name);
        values.put(MyDbConstru.CreateDB.AGE,age);
        values.put(MyDbConstru.CreateDB.CONTEXTS,contexts);
        return mDB.insert(MyDbConstru.CreateDB._TABLENAME0,null,values)>0;
    }
    // Delete All
    public void deleteAllColumns() {
        mDB.delete(MyDbConstru.CreateDB._TABLENAME0, null, null);
    }

    // Delete Column
    public boolean deleteColumn(long id){
        return mDB.delete(MyDbConstru.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }*/}

