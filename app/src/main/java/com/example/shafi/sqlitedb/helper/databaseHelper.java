package com.example.shafi.sqlitedb.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY, "+NAME+" VARCHAR(30));";
    private static final int VERSION = 1;
    private Context context;

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, "Exception "+e, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);


        }catch (Exception e){
            Toast.makeText(context, "Exception "+e, Toast.LENGTH_SHORT).show();

        }

    }

    public long saveData(String id1, String name1) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id1);
        contentValues.put(NAME,name1);
        long rowNumber = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowNumber;


    }

    public Cursor showAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }


    public Boolean updateData(String id, String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        sqLiteDatabase.update(TABLE_NAME, contentValues,ID+" = ?", new  String[] {id});

        return true;
    }



    public int deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME, ID+" = ?", new String[] {id});
        return value;
    }
}
