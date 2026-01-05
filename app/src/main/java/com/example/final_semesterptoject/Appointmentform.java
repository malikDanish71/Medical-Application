package com.example.final_semesterptoject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Appointmentform extends AppCompatActivity {

    // Declare the EditText, AutoCompleteTextView, and Button variables
    private EditText reasonEditText, whenEditText, firstNameEditText, lastNameEditText, emailEditText, additionalInfoEditText;
    private Button submitButton, homeButton;
    private AutoCompleteTextView doctorNameEditText;

    // Initialize a list of doctors' names
    private List<String> doctorList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentform);

        // Initialize the UI elements using findViewById
        reasonEditText = findViewById(R.id.reason);
        whenEditText = findViewById(R.id.when);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        additionalInfoEditText = findViewById(R.id.additionalInfo);
        submitButton = findViewById(R.id.submitButton);
        homeButton = findViewById(R.id.buttonhome);
        doctorNameEditText = findViewById(R.id.doctorName);  // AutoCompleteTextView for doctor name

        // Initialize the list of doctors
        doctorList = new ArrayList<>();
        doctorList.add("Dr. M Ali");
        doctorList.add("Dr. Rhan Ch");
        doctorList.add("Dr. Hamza Malik");
        doctorList.add("Dr. M Wajid Gujjar");
        doctorList.add("Dr. Adeel Khan");
        doctorList.add("Dr. Sania Batool");

        // Create an ArrayAdapter for the AutoCompleteTextView
        ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, doctorList);
        doctorNameEditText.setAdapter(doctorAdapter);  // Set the adapter to the AutoCompleteTextView

        // Set the submit button's onClick listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processForm();
            }
        });

        // Set the home button's onClick listener
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateHome();
            }
        });
    }

    // Method to handle form submission
    private void processForm() {
        // Collect the data from the EditText fields
        String reason = reasonEditText.getText().toString();
        String date = whenEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String additionalInfo = additionalInfoEditText.getText().toString();
        String doctorName = doctorNameEditText.getText().toString();  // Get the selected doctor name

        // Ensure the doctor name is not empty before proceeding
        if (doctorName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please select a doctor", Toast.LENGTH_SHORT).show();
            return;
        }


       // dataholder obj = new dataholder(reason, date, doctorName);

        // Get a reference to FirebaseDatabase and the node to store the data
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("Appointmentform");

        //node.child(firstName).setValue(obj);

        // Show a Toast message to indicate form submission
        Toast.makeText(getApplicationContext(), "Form Submitted", Toast.LENGTH_SHORT).show();
    }

    private void navigateHome() {
        Intent intent = new Intent(Appointmentform.this, MainActivity.class);
        startActivity(intent);
    }
}
