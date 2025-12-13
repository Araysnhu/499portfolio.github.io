package com.example.weighttrackapp.ui.theme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "WeightTracker.db";
    private static final int DATABASE_VERSION = 2;

    // Table names
    private static final String TABLE_WEIGHT_ENTRIES = "weight_entries";
    private static final String TABLE_USERS = "users";

    // Common column names
    private static final String COLUMN_ID = "_id";

    // WEIGHT_ENTRIES table columns
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_WEIGHT_GOAL = "weight_goal";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_USER_ID = "user_id";

    // USERS table columns
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Table creation SQL statements
    private static final String CREATE_WEIGHT_ENTRIES_TABLE =
            "CREATE TABLE " + TABLE_WEIGHT_ENTRIES + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_DATE + " TEXT NOT NULL," +
                    COLUMN_WEIGHT + " REAL NOT NULL," +
                    COLUMN_WEIGHT_GOAL + " REAL," +
                    COLUMN_NOTES + " TEXT," +
                    COLUMN_USER_ID + " TEXT NOT NULL" +
                    ");";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                    COLUMN_PASSWORD + " TEXT NOT NULL" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WEIGHT_ENTRIES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEIGHT_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Method to check if a user exists
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_USERNAME},
                COLUMN_USERNAME + "=?",
                new String[]{username},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Method to add a new user
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    // Method to check if a user exists (for login)
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_USERNAME},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Method to add a new weight entry for a specific user
    public boolean addWeightEntry(WeightEntry weightEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, weightEntry.getDate());
        values.put(COLUMN_WEIGHT, weightEntry.getWeight());
        values.put(COLUMN_WEIGHT_GOAL, weightEntry.getWeightGoal());
        values.put(COLUMN_NOTES, weightEntry.getNotes());
        values.put(COLUMN_USER_ID, weightEntry.getUserId());
        long result = db.insert(TABLE_WEIGHT_ENTRIES, null, values);
        db.close();
        return result != -1;
    }

    // Method to get all weight entries for the logged-in user
    public List<WeightEntry> getAllWeightEntries(String userId) {
        List<WeightEntry> weightList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WEIGHT_ENTRIES,
                new String[]{COLUMN_ID, COLUMN_DATE, COLUMN_WEIGHT, COLUMN_WEIGHT_GOAL, COLUMN_NOTES, COLUMN_USER_ID},
                COLUMN_USER_ID + "=?",
                new String[]{userId},
                null, null, COLUMN_DATE + " DESC");

        if (cursor.moveToFirst()) {
            do {
                WeightEntry weightEntry = new WeightEntry(
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                weightEntry.setId(cursor.getInt(0));
                weightList.add(weightEntry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return weightList;
    }
}