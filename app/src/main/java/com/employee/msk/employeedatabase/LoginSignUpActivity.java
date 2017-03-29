package com.employee.msk.employeedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.employee.msk.employeedatabase.data.EmployeeContract;

/**
 * Created by DELL on 3/29/2017.
 */

public class LoginSignUpActivity extends AppCompatActivity {


    private Button mRegistered , mSignIn;
    EditText mEmail,mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        mEmail = (EditText) findViewById(R.id.email);
        mPass = (EditText) findViewById(R.id.pass);
        mRegistered = (Button) findViewById(R.id.register);
        mSignIn = (Button) findViewById(R.id.LoginButton);


        mRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginSignUpActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatabaseInfo();
            }
        });


    }


    private void displayDatabaseInfo() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                EmployeeContract.EmployeeEntry._ID,
                EmployeeContract.EmployeeEntry.COLUMN_EMPLOYE_NAME,
                EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_EMAIL,
                EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_GENDER,
                EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_Password };

        // Perform a query on the provider using the ContentResolver.
        // Use the {@link EmployeeContract.EmployeeEntry#CONTENT_URI} to access the pet data.
        Cursor cursor = getContentResolver().query(
                EmployeeContract.EmployeeEntry.CONTENT_URI,   // The content URI of the words table
                projection,             // The columns to return for each row
                null,                   // Selection criteria
                null,                   // Selection criteria
                null);                  // The sort order for the returned rows



        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
//            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
//            displayView.append(EmployeeContract.EmployeeEntry._ID + " - " +
//                    EmployeeContract.EmployeeEntry.COLUMN_PET_NAME + " - " +
//                    EmployeeContract.EmployeeEntry.COLUMN_PET_BREED + " - " +
//                    EmployeeContract.EmployeeEntry.COLUMN_PET_GENDER + " - " +
//                    EmployeeContract.EmployeeEntry.COLUMN_PET_WEIGHT + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(EmployeeContract.EmployeeEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYE_NAME);
            int emailColumnIndex = cursor.getColumnIndex(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_EMAIL);
            int genderColumnIndex = cursor.getColumnIndex(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_GENDER);
            int passColumnIndex = cursor.getColumnIndex(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_Password);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentEmail = cursor.getString(emailColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                String currentPass = cursor.getString(passColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView


                String matchemail = mEmail.getText().toString();
                String matchpass = mPass.getText().toString();



                if(matchemail.equals(currentEmail) && matchpass.equals(currentPass)){

                    Intent intent = new Intent(LoginSignUpActivity.this, HomeActivity.class);
                    startActivity(intent);

                }






            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }




}
