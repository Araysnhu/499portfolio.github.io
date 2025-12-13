package com.example.weighttrackapp.ui.theme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHelper: Manages creation, upgrade, and all CRUD operations for the SQLite database.
 * Uses a Contract Pattern for table and column definitions to improve maintainability and prevent magic strings.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database configuration
    private static final String DATABASE_NAME = "WeightTracker.db";
    private static final int DATABASE_VERSION = 2;

    // Inner class defining table and column names (Contract Pattern - excellent engineering practice)
    public static abstract class DbContract {
        // Common column
        public static final String COLUMN_ID = "_id";

        // USERS table
        public static final String TABLE_USERS = "users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";

        // WEIGHT_ENTRIES table
        public static final String TABLE_WEIGHT_ENTRIES = "weight_entries";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_WEIGHT_GOAL = "weight_goal";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_USER_ID = "user_id";
    }

    // Table creation SQL statements using the contract constants
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + DbContract.TABLE_USERS + "(" +
                    DbContract.COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                    DbContract.COLUMN_PASSWORD + " TEXT NOT NULL" +
                    ");";

    private static final String CREATE_WEIGHT_ENTRIES_TABLE =
            "CREATE TABLE " + DbContract.TABLE_WEIGHT_ENTRIES + "(" +
                    DbContract.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DbContract.COLUMN_DATE + " TEXT NOT NULL," +
                    DbContract.COLUMN_WEIGHT + " REAL NOT NULL," +
                    DbContract.COLUMN_WEIGHT_GOAL + " REAL," +
                    DbContract.COLUMN_NOTES + " TEXT," +
                    DbContract.COLUMN_USER_ID + " TEXT NOT NULL" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_WEIGHT_ENTRIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_WEIGHT_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_USERS);
        onCreate(db);
    }

    /**
     * Checks if a user with the given username already exists.
     * @param username The username to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DbContract.TABLE_USERS,
                new String[]{DbContract.COLUMN_USERNAME},
                DbContract.COLUMN_USERNAME + "=?",
                new String[]{username},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    /**
     * Adds a new user to the database.
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @return true if the user was added successfully, false otherwise.
     */
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.COLUMN_USERNAME, username);
        values.put(DbContract.COLUMN_PASSWORD, password);

        long result = db.insert(DbContract.TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    /**
     * Validates user credentials for login.
     * @param username The username provided.
     * @param password The password provided.
     * @return true if the credentials are valid, false otherwise.
     */
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DbContract.TABLE_USERS,
                new String[]{DbContract.COLUMN_USERNAME},
                DbContract.COLUMN_USERNAME + "=? AND " + DbContract.COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    /**
     * Adds a new weight entry to the database.
     * @param weightEntry The WeightEntry object to save.
     * @return true if the entry was added successfully, false otherwise.
     */
    public boolean addWeightEntry(WeightEntry weightEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.COLUMN_DATE, weightEntry.getDate());
        values.put(DbContract.COLUMN_WEIGHT, weightEntry.getWeight());
        values.put(DbContract.COLUMN_WEIGHT_GOAL, weightEntry.getWeightGoal());
        values.put(DbContract.COLUMN_NOTES, weightEntry.getNotes());
        values.put(DbContract.COLUMN_USER_ID, weightEntry.getUserId());
        long result = db.insert(DbContract.TABLE_WEIGHT_ENTRIES, null, values);
        db.close();
        return result != -1;
    }

    /**
     * Retrieves all weight entries for a specific user, ordered by date descending.
     * @param userId The username (ID) of the logged-in user.
     * @return A list of WeightEntry objects.
     */
    public List<WeightEntry> getAllWeightEntries(String userId) {
        List<WeightEntry> weightList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DbContract.TABLE_WEIGHT_ENTRIES,
                new String[]{DbContract.COLUMN_ID, DbContract.COLUMN_DATE, DbContract.COLUMN_WEIGHT, DbContract.COLUMN_WEIGHT_GOAL, DbContract.COLUMN_NOTES, DbContract.COLUMN_USER_ID},
                DbContract.COLUMN_USER_ID + "=?",
                new String[]{userId},
                null, null, DbContract.COLUMN_DATE + " DESC");

        if (cursor.moveToFirst()) {
            do {
                WeightEntry weightEntry = new WeightEntry(
                        cursor.getString(1), // Date
                        cursor.getDouble(2), // Weight
                        cursor.getDouble(3), // WeightGoal
                        cursor.getString(4), // Notes
                        cursor.getString(5)  // UserId
                );
                weightEntry.setId(cursor.getInt(0)); // ID
                weightList.add(weightEntry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return weightList;
    }
}