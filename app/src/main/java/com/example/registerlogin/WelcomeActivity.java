package com.example.registerlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView userInfoText;
    private Button logoutButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        
        userInfoText = findViewById(R.id.userInfoText);
        logoutButton = findViewById(R.id.logoutButton);

        String username = getIntent().getStringExtra("username");
        displayUserInfo(username);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void displayUserInfo(String username) {
        String fullName = preferences.getString(username + "_fullName", "");
        String gender = preferences.getString(username + "_gender", "");
        int birthYear = preferences.getInt(username + "_birthYear", 0);

        String userInfo = "Name: " + fullName + "\n" +
                         "Gender: " + gender + "\n" +
                         "Birth Year: " + birthYear + "\n" +
                         "Username: " + username;

        userInfoText.setText(userInfo);
    }
}
