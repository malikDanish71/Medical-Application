package com.example.final_semesterptoject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etRegEmail, etRegPassword;
    private Button btnRegister;
    private TextView tvLoginPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etRegEmail = findViewById(R.id.et_reg_email);
        etRegPassword = findViewById(R.id.et_reg_password);
        btnRegister = findViewById(R.id.btn_register);
        tvLoginPrompt = findViewById(R.id.tv_login_prompt);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        tvLoginPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String email = etRegEmail.getText().toString().trim();
        String password = etRegPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password is required.");
            return;
        }

        // AsyncTask to perform database operations on a background thread
        new AsyncTask<Void, Void, Boolean>() {
            private boolean userExists = false;

            @Override
            protected Boolean doInBackground(Void... voids) {
                // Check if user already exists
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                if (db.userDao().findByEmail(email) != null) {
                    userExists = true;
                    return false;
                }

                // Create and insert new user
                User user = new User();
                user.email = email;
                user.password = password; // In a real app, you should hash the password!
                db.userDao().insert(user);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    Toast.makeText(RegistrationActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    finish();
                } else {
                    if (userExists) {
                        Toast.makeText(RegistrationActivity.this, "User already exists.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.execute();
    }
}
