package com.example.mikio.kimya_3_0_Yevlakh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button buttonUser = (Button) findViewById(R.id.ButtonUser);
        Button buttonAdmin = (Button) findViewById(R.id.ButtonAdmin);

        buttonUser.setOnClickListener(this);
        buttonAdmin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtonUser:
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("checkUser", "isUser");
                startActivity(i);
                finish();
                break;
            case R.id.ButtonAdmin:
                Intent iL = new Intent(this, LoginActivity.class);
                startActivity(iL);
                finish();
                break;

        }
    }
}
