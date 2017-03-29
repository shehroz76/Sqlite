package com.employee.msk.employeedatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.employee.msk.employeedatabase.data.EmployeeContract.EmployeeEntry;

/**
 * Created by DELL on 3/29/2017.
 */

public class EmployeeDbHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = EmployeeDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "employeedatabse.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;



    public EmployeeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


// Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + EmployeeEntry.TABLE_NAME + " ("
                + EmployeeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +EmployeeEntry.COLUMN_EMPLOYE_NAME + " TEXT NOT NULL, "
                + EmployeeEntry.COLUMN_EMPLOYEE_EMAIL + " TEXT, "
                + EmployeeEntry.COLUMN_EMPLOYEE_GENDER + " INTEGER NOT NULL, "
                + EmployeeEntry.COLUMN_EMPLOYEE_Password + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
