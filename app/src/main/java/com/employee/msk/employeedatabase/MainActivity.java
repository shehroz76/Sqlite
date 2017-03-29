package com.employee.msk.employeedatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.employee.msk.employeedatabase.data.EmployeeContract;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;

    /** EditText field to enter the pet's breed */
    private EditText mEmailEditText;

    /** EditText field to enter the pet's weight */
    private EditText mPassEditText;

    /** EditText field to enter the pet's gender */
    private Spinner mGenderSpinner;

    private Button Registered;


    private int mGender = EmployeeContract.EmployeeEntry .GENDER_UNKNOWN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        mNameEditText = (EditText) findViewById(R.id.name_r);
        mEmailEditText = (EditText) findViewById(R.id.email_r);
        mPassEditText = (EditText) findViewById(R.id.passR);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        Registered = (Button) findViewById(R.id.registerR);

        Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPet();

                Intent intent = new Intent(MainActivity.this, LoginSignUpActivity.class);
                startActivity(intent);

            }
        });

        setupSpinner();

    }


    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = EmployeeContract.EmployeeEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = EmployeeContract.EmployeeEntry.GENDER_FEMALE;
                    } else {
                        mGender = EmployeeContract.EmployeeEntry.GENDER_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = EmployeeContract.EmployeeEntry.GENDER_UNKNOWN;
            }
        });
    }



    private void insertPet() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String emailString = mEmailEditText.getText().toString().trim();
        String passString = mPassEditText.getText().toString().trim();


        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYE_NAME, nameString);
        values.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_EMAIL, emailString);
        values.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_GENDER, mGender);
        values.put(EmployeeContract.EmployeeEntry.COLUMN_EMPLOYEE_Password, passString);


        // Insert a new pet into the provider, returning the content URI for the new pet.
        Uri newUri = getContentResolver().insert(EmployeeContract.EmployeeEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, getString(R.string.editor_insert_pet_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.editor_insert_pet_successful),
                    Toast.LENGTH_SHORT).show();
        }
    }





}
