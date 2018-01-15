package com.example.mikio.kimya_3_0_Yevlakh;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button buttonManuelleSuche = (Button) findViewById(R.id.buttonManuelleSuche);
        Button buttonQRSearch = (Button) findViewById(R.id.buttonQRSearch);
        Button buttonForm = (Button) findViewById(R.id.buttonForm);
        Button buttonTestCall = (Button) findViewById(R.id.buttenTestCall);


        buttonManuelleSuche.setOnClickListener(this);
        buttonQRSearch.setOnClickListener(this);
        buttonForm.setOnClickListener(this);
        buttonTestCall.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonManuelleSuche:
                Intent iM = new Intent(this, ManuelleSucheActivity.class);
                startActivity(iM);
                finish();
                break;
            case R.id.buttonQRSearch:
//                String qrSearchResult = "http://cabinporn.com/";
//                if(qrSearchResult!=null){
//                    Utils.openBrowser(view.getContext(), qrSearchResult);
//                    return;
//                }
//                Toast.makeText(view.getContext(), "Im Suchergebnis war keine URL enthalten :(", Toast.LENGTH_LONG).show();
                Intent iS = new Intent(this, ScanActivity.class);
                startActivity(iS);
                finish();
                break;
            case R.id.buttonForm:
                Intent iF = new Intent(this, FormActivity.class);
                startActivity(iF);
                finish();
                break;
            case R.id.buttenTestCall:
                Intent iTC = new Intent(this, HttpTestActivity.class);
                startActivity(iTC);
                finish();
                break;

        }
    }
}
