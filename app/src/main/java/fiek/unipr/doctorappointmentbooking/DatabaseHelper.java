package fiek.unipr.doctorappointmentbooking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "DAB_DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Users(Id INTEGER PRIMARY KEY AUTOINCREMENT,FullName TEXT, MobileNumber NUMBER, Email TEXT, Password TEXT)");
        db.execSQL("CREATE TABLE EventCalendar(Date TEXT, Event TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Users");
        db.execSQL("drop table if exists EventCalendar");
    }


}