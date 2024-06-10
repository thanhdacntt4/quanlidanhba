package com.example.baiktra1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qlcontacts.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTblDonVi = "CREATE TABLE tblDonVi (" +
                "maDonVi TEXT PRIMARY KEY," +
                "tenDonVi TEXT," +
                "email TEXT," +
                "website TEXT," +
                "logo TEXT," +
                "diaChi TEXT," +
                "soDienThoai TEXT," +
                "maDonViCha TEXT)";
        db.execSQL(createTblDonVi);

        String createTblNhanVien = "CREATE TABLE tblNhanVien (" +
                "maNhanVien TEXT PRIMARY KEY," +
                "hoTen TEXT," +
                "chucVu TEXT," +
                "email TEXT," +
                "soDienThoai TEXT," +
                "anhDaiDien TEXT," +
                "maDonVi TEXT," +
                "FOREIGN KEY(maDonVi) REFERENCES tblDonVi(maDonVi))";
        db.execSQL(createTblNhanVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblDonVi");
        db.execSQL("DROP TABLE IF EXISTS tblNhanVien");
        onCreate(db);
    }
}
