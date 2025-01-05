package com.example.registerlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameInput, passwordInput;
    private Button loginButton;
    private TextView registerLink;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        
        usernameInput = findViewById(R.id.loginUsername);
        passwordInput = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerLink);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (validateLogin(username, password)) {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        String storedPassword = preferences.getString(username + "_password", "");
        return !username.isEmpty() && !password.isEmpty() && password.equals(storedPassword);
    }
}