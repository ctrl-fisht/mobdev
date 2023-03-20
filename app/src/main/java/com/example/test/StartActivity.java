package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.test.data.Cities;

public class StartActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cityCursor;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_IS_NEW = "isnew";

    SharedPreferences mSettings;


    private Cursor query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (!mSettings.getBoolean(APP_PREFERENCES_IS_NEW, true))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        // database init
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Spinner items
        Spinner spinner = findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                Cities.getCities());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void startGo(View view){
        Spinner spinner = findViewById(R.id.spinner5);
        String selected_city = spinner.getSelectedItem().toString();

        if (selected_city.equals("")){
            return;
        }

        db = databaseHelper.getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO cities VALUES (0, '" + selected_city + "');");

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(APP_PREFERENCES_IS_NEW, false);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
    @Override
    public void onDestroy(){
        super.onDestroy();

        db.close();
        cityCursor.close();
    }
}