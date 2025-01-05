package com.example.registerlogin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText fullNameInput, birthYearInput, usernameInput, passwordInput, confirmPasswordInput;
    private RadioGroup genderGroup;
    private Button registerButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferences = getSharedPreferences("UserData", MODE_PRIVATE);

        fullNameInput = findViewById(R.id.fullName);
        birthYearInput = findViewById(R.id.birthYear);
        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        confirmPasswordInput = findViewById(R.id.confirmPassword);
        genderGroup = findViewById(R.id.genderGroup);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    saveUser();
                    finish();
                }
            }
        });
    }

    private boolean validateInputs() {
        String fullName = fullNameInput.getText().toString();
        String birthYear = birthYearInput.getText().toString();
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String confirmPassword = confirmPasswordInput.getText().toString();

        if (fullName.isEmpty() || birthYear.isEmpty() || username.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty() || 
            genderGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (preferences.contains(username + "_password")) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int year = Integer.parseInt(birthYear);
            if (year < 1900 || year > 2024) {
                Toast.makeText(this, "Invalid birth year", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid birth year format", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void saveUser() {
        String fullName = fullNameInput.getText().toString();
        int birthYear = Integer.parseInt(birthYearInput.getText().toString());
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String gender = genderGroup.getCheckedRadioButtonId() == R.id.maleRadio ? "Male" : "Female";

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(username + "_fullName", fullName);
        editor.putString(username + "_gender", gender);
        editor.putInt(username + "_birthYear", birthYear);
        editor.putString(username + "_password", password);
        editor.apply();

        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
    }
}
