package com.example.mikio.kimya_3_0_Yevlakh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent iStart = new Intent(this, StartActivity.class);
        startActivity(iStart);
        finish();
    }

    public void checkLoginData(View v) {
        username = (EditText)findViewById(R.id.input_username);
        password = (EditText)findViewById(R.id.input_password);

        String usernameContent = username.getText().toString();
        String passwordContent = password.getText().toString();


        if (usernameContent.equals("Admin") && passwordContent.equals("passwort")){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("checkUser", "isAdmin");
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, "Login fehlgeschlafen. Benutzername oder Passwort falsch.", Toast.LENGTH_SHORT).show();
        }
    }
}
