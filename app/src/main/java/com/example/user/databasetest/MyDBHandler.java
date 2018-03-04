package com.example.user.databasetest;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCTNAME + " TEXT" + ");";

        //execute the query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }


    //product is the class that will be stored
    public void addProduct(Products product){
        //sets different values for different columns and makes inserting easy
        ContentValues values = new ContentValues();
        //2 paramaters. first column. second value
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        //database item
        SQLiteDatabase db = getWritableDatabase();
        //purely an insert statement rather than executing a query. 3 parameters
        //name of table, optional null?, list of values or contentvalues
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete from database
    public void deleteProduct(String productName){
        //database item
        SQLiteDatabase db = getWritableDatabase();
        //delete item where itemname is the same as what was entered
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //Print database as a string
    public String databaseToString(){
        String dbString = "";
        //database item
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("productname")) != null) {
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            c.moveToNext();
        }

        db.close();
        return dbString;

    }
}