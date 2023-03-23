package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_CITY};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public City getCity(int id){
        City city = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String f_city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY));
            city = new City(id, f_city);
        }
        cursor.close();
        return  city;
    }

    public List<City> getCities(){
        ArrayList<City> cities = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY));
            cities.add(new City(id, city));
        }
        cursor.close();
        return  cities;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public long insert(int id, String city){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_ID, id);
        cv.put(DatabaseHelper.COLUMN_CITY, city);

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long cityId){

        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(cityId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(City city){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + city.id;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_CITY, city.city);
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }


}
