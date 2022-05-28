package com.example.se328_project;


import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "studentsDB";
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_STUDENTNAME = "StudentName";
        public static final String COLUMN_SURNAME = "SurName";
        public static final String COLUMN_FATHERNAME = "FatherName";
        public static final String COLUMN_NATIONALID = "NationalID";
        public static final String COLUMN_ID = "ID";

        public DatabaseHelper(Context context){

            super(context,DATABASE_NAME,null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "("+ COLUMN_STUDENTNAME +
                    " TEXT PRIMARY KEY," + COLUMN_SURNAME+ " TEXT NOT NULL," +
                    COLUMN_FATHERNAME + " TEXT NOT NULL, " + COLUMN_NATIONALID +
                    " TEXT NOT NULL, " + COLUMN_ID + " TEXT NOT NULL)");
        }


        public boolean updatestudents(String sname, String surname, String fname,String ID) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_STUDENTNAME,sname);
            values.put(COLUMN_SURNAME,surname);
            values.put(COLUMN_FATHERNAME,fname);
            values.put(COLUMN_ID,ID);
            db.update(TABLE_NAME, values, "ID = ?", new String[] { ID });

            return true;

        }

        public void AddStudents(String sname, String surname, String fname, String nID, String ID){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_STUDENTNAME, sname);
            values.put(COLUMN_SURNAME, surname);
            values.put(COLUMN_FATHERNAME, fname);
            values.put(COLUMN_NATIONALID, nID);
            values.put(COLUMN_ID, ID);
            db.insert(TABLE_NAME, null, values);
        }

        public Cursor ViewStudents(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor x = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
            return x ;
        }

        public void Deletestudents(String id){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, "ID = ?", new String[] {id});
        }



        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
        }
    }


