package com.example.alberto.inmigrationform.MainActivity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alberto.inmigrationform.MainActivity.Parcelable.User;

import java.util.ArrayList;

/**
 * Created by Alberto on 12/12/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserDetails";

    // Contacts table name
    private static final String TABLE_USER = "User";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_lastName = "lastName";
    private static final String KEY_DOB = "dob";
    private static final String KEY_Gender = "gender";
    private static final String KEY_Country = "country";
    private static final String KEY_Address = "address";
    private static final String KEY_EMAIL = "email";
    private final ArrayList<User> user_list = new ArrayList<User>();

    //Constructor db
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                + KEY_lastName + " TEXT, " + KEY_DOB + " TEXT, " + KEY_Gender + " TEXT, "
                + KEY_Country + " TEXT, " + KEY_Address + " TEXT, " + KEY_EMAIL + " TEXT" + " )";
        db.execSQL(CREATE_USER_TABLE);

    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);

    }

//Methods for the database
    // Adding new user inmigrant
    public void Add_User(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getmUName()); // Inmigrant Name
        values.put(KEY_lastName, user.getmULast()); // Inmigrant lastName
        values.put(KEY_DOB, user.getmUDOB());//Inmigrant date if birth
        values.put(KEY_Gender, user.getmUGender());//Inmigrant gender
        values.put(KEY_Country, user.getmUCountry());//Inmigrant country
        values.put(KEY_Address, user.getmtxtArea());//Inmigrant address
        values.put(KEY_EMAIL, user.getmUtxtEmail()); // Inmigrant Email
        // Inserting Row
        long id = 0;
        id = db.insert(TABLE_USER, null, values);
        if(id<0){
            Log.e("db", "error" + id);
        } else{
            Log.i("db", "Correct add user");
        }
        db.close(); // Closing database connection
    }

    // Getting All Contacts
    public ArrayList<User> Get_Users() {
        try {
            user_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_USER;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setmUID(Integer.parseInt(cursor.getString(0)));
                    user.setmUName(cursor.getString(1));
                    user.setmULast(cursor.getString(2));
                    user.setmUDOB(cursor.getString(3));
                    user.setmUGender(cursor.getString(4));
                    user.setmUCountry(cursor.getString(5));
                    user.setmUtxtAdress(cursor.getString(6));
                    user.setmUtxtEmail(cursor.getString(7));
                    // Adding contact to list
                    user_list.add(user);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            //return user_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_users", "" + e);
        }
        return user_list;
    }
}
