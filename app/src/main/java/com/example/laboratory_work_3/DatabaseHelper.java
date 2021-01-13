package com.example.laboratory_work_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "Laboratory_work_3";

    private static final String INSTITUTION_TABLE_NAME = "Institution";
    private static final String COLUMN_ID_INSTITUTION = "Id_institution";
    private static final String COLUMN_URL_INSTITUTION = "Url_institution";
    private static final String COLUMN_KEY_INSTITUTION = "Key_institution";

    private static final String NAME_TABLE_NAME = "Name";
    private static final String COLUMN_ID_NAME = "Id_name";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_LABEL = "Label";
    private static final String COLUMN_TYPE = "Type";

    private static final String LOCATION_TABLE_NAME = "Location";
    private static final String COLUMN_ID_LOCATION = "Id_location";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_COUNTRY = "Country";
    private static final String COLUMN_STATE = "State";
    private static final String COLUMN_CITY = "City";
    private static final String COLUMN_LATITUDE = "Latitude";
    private static final String COLUMN_LONGITUDE = "Longitude";

    private static final String IDENTIFIERS_TABLE_NAME = "Identifiers";
    private static final String COLUMN_ID_IDENTIFIERS = "Id_identifiers";
    private static final String COLUMN_IDENTIFIERS = "Identifiers";
    private static final String COLUMN_BASE = "Base";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + INSTITUTION_TABLE_NAME + "("
                + COLUMN_ID_INSTITUTION + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_URL_INSTITUTION + " TEXT,"
                + COLUMN_KEY_INSTITUTION + " TEXT"
                + ")");
        db.execSQL("CREATE TABLE " + NAME_TABLE_NAME + "("
                + COLUMN_ID_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_LABEL + " TEXT,"
                + COLUMN_TYPE + " TEXT"
                + ")");

        db.execSQL("CREATE TABLE " + LOCATION_TABLE_NAME + "("
        + COLUMN_ID_LOCATION + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + COLUMN_LOCATION + " TEXT,"
        + COLUMN_COUNTRY + " TEXT," + COLUMN_STATE + " TEXT,"
        + COLUMN_CITY + " TEXT,"
        + COLUMN_LATITUDE + " REAL,"
        + COLUMN_LONGITUDE + " REAL"
        + ")");

        db.execSQL("CREATE TABLE " + IDENTIFIERS_TABLE_NAME + "("
                + COLUMN_ID_IDENTIFIERS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_IDENTIFIERS + " TEXT,"
                + COLUMN_BASE + " TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INSTITUTION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NAME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IDENTIFIERS_TABLE_NAME);
        onCreate(db);
    }

    public long addInstitutionRow(String url, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL_INSTITUTION, url);
        values.put(COLUMN_KEY_INSTITUTION, key);
        long result = db.insert(INSTITUTION_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public ArrayList<Institution> getAllInstitutionRows() {
        ArrayList<Institution> institutionArrayList = new ArrayList<Institution>();
        String institutionScript = "SELECT * FROM " + INSTITUTION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(institutionScript, null);
        if (cursor.moveToFirst()) {
            do {
                Institution values = new Institution();
                values.url = cursor.getString(1);
                values.key = cursor.getString(2);
                institutionArrayList.add(values);
            } while (cursor.moveToNext());
        }
    return institutionArrayList;
    }

    public long addNameRow(String name, String label, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_LABEL, label);
        values.put(COLUMN_TYPE, type);
        long result = db.insert(NAME_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public ArrayList<Name> getAllNameRows() {
        ArrayList<Name> nameArrayList = new ArrayList<Name>();
        String nameScript = "SELECT * FROM " + NAME_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(nameScript, null);
        if (cursor.moveToFirst()) {
            do {
                Name values = new Name();
                values.name = cursor.getString(1);
                values.label = cursor.getString(2);
                values.type = cursor.getString(3);
                nameArrayList.add(values);
            } while (cursor.moveToNext());
        }
        return nameArrayList;
    }

    public long addLocationRow(String location, String country, String state, String city, float latitude, float longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_COUNTRY, country);
        values.put(COLUMN_STATE, state);
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        long result = db.insert(LOCATION_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public ArrayList<Location> getAllLocationRows() {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();
        String locationScript = "SELECT * FROM " + LOCATION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(locationScript, null);
        if (cursor.moveToFirst()) {
            do {
                Location values = new Location();
                values.location = cursor.getString(1);
                values.country = cursor.getString(2);
                values.state = cursor.getString(3);
                values.city = cursor.getString(4);
                values.latitude = cursor.getFloat(5);
                values.longitude = cursor.getFloat(6);
                locationArrayList.add(values);
            } while (cursor.moveToNext());
        }
        return locationArrayList;
    }

    public long addIdentifiersRow(String identifier, String base) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDENTIFIERS, identifier);
        values.put(COLUMN_BASE, base);
        long result = db.insert(IDENTIFIERS_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public ArrayList<Identifiers> getAllIdertifiersRows() {
        ArrayList<Identifiers> identifiersArrayList = new ArrayList<Identifiers>();
        String identifiersScript = "SELECT * FROM " + IDENTIFIERS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(identifiersScript, null);
        if (cursor.moveToFirst()) {
            do {
                Identifiers values = new Identifiers();
                values.identifier = cursor.getString(1);
                values.base = cursor.getString(2);
                identifiersArrayList.add(values);
            } while (cursor.moveToNext());
        }
        return identifiersArrayList;
    }
}
