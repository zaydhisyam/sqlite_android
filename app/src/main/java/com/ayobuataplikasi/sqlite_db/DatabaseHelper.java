package com.ayobuataplikasi.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "database_mahasiswa";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "mahasiswa";
    private static final String KEY_NIM = "nim";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NOHP = "hp";

    private static final String CREATE_TABLE_MHS = "CREATE TABLE " + TABLE_USER +
            "(" + KEY_NIM + " TEXT PRIMARY KEY, "
            + KEY_NAMA + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_NOHP + " TEXT);";

//    private static final String CREATE_TABLE_MHS = "CREATE TABLE " + TABLE_USER + "(" + KEY_NIM + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE_MHS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MHS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public long addMhs(String nim, String nama, String email, String noHp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIM, nim);
        values.put(KEY_NAMA, nama);
        values.put(KEY_EMAIL, email);
        values.put(KEY_NOHP, noHp);
        long insert = db.insert(TABLE_USER, null, values);
        return insert;
    }

    public ArrayList<mahasiswa> getAllMhs() {
        ArrayList<mahasiswa> arrayList = new ArrayList<mahasiswa>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                mahasiswa mhs = new mahasiswa();
                mhs.setNim(c.getString(c.getColumnIndex(KEY_NIM)));
                mhs.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
                mhs.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                mhs.setNoHp(c.getString(c.getColumnIndex(KEY_NOHP)));
                arrayList.add(mhs);
            } while (c.moveToNext());
        }
        return arrayList;
    }

    public int updateMhs(String nim, String nama, String email, String noHp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_EMAIL, email);
        values.put(KEY_NOHP, noHp);
        int update = db.update(TABLE_USER, values, KEY_NIM + " = ?", new String[]{String.valueOf(nim)});
        return update;
    }

    public void deleteMhs(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_NIM + " = ?", new String[]{String.valueOf(nim)});
    }
}
