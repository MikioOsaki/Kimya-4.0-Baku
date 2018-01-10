    package com.example.mikio.kimya_3_0_Yevlakh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

    public class FormActivity extends AppCompatActivity implements View.OnClickListener {

        Spinner anlage_spinner;
        Spinner einheit_spinner;
        ArrayAdapter<CharSequence> anlage_adapter;
        ArrayAdapter<CharSequence> einheit_adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form);
            anlage_spinner = findViewById(R.id.Anlage_spinner);
            anlage_adapter = ArrayAdapter.createFromResource(this,
                    R.array.anlagen_array, android.R.layout.simple_spinner_item);
            anlage_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            anlage_spinner.setAdapter(anlage_adapter);

            einheit_spinner = findViewById(R.id.Einheit_spinner);
            einheit_adapter = ArrayAdapter.createFromResource(this,
                    R.array.einheit_array, android.R.layout.simple_spinner_item);
            einheit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            einheit_spinner.setAdapter(einheit_adapter);

        }

        @Override
        protected void onResume() {
            super.onResume();

            Button buttonSave = (Button) findViewById(R.id.buttonSpeichernAnlage);
            buttonSave.setOnClickListener(this);
        }

            @Override
            public void onBackPressed() {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.buttonSpeichernAnlage:
                        // Instantiate the RequestQueue.
                        RequestQueue queue = Volley.newRequestQueue(this);
                        String url = "http://192.168.1.100:8001/kimya_test/insert.php";
                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the first 500 characters of the response string.
                                        Toast.makeText(FormActivity.this, "Response is: "+response, Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(FormActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);

                        Toast.makeText(this, "Anwendung wurde erfolgreich gespeichert.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
