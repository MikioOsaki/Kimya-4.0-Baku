package com.example.mikio.kimya_3_0_Yevlakh;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.Spinner;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private Context context;
    public List<String> spinnerNameList = new ArrayList<String>();
    public List<String> spinnerItemsList = new ArrayList<String>();

    Spinner anlage_spinner;
    Spinner einheit_spinner;
    Spinner wirkdauer_spinner;
    Spinner wirkflaeche_spinner;
    Spinner verwMenge_spinner;
    Spinner freisetz_spinner;
    Spinner anwendungsbereich_spinner;
    Spinner verfahren_spinner;
    Spinner produktkateg_spinner;
    Spinner PROC_spinner;
    Spinner material_spinner;
    Spinner luftversorg_spinner;
    Spinner haeufigk_spinner;
    Spinner staubung_spinner;
    ArrayAdapter<CharSequence> anlage_adapter;
    ArrayAdapter<CharSequence> einheit_adapter;
    ArrayAdapter<CharSequence> wirkdauer_adapter;
    ArrayAdapter<CharSequence> wirkflaeche_adapter;
    ArrayAdapter<CharSequence> verwMenge_adapter;
    ArrayAdapter<CharSequence> freisetz_adapter;
    ArrayAdapter<CharSequence> anwendungsbereich_adapter;
    ArrayAdapter<CharSequence> verfahren_adapter;
    ArrayAdapter<CharSequence> produktkateg_adapter;
    ArrayAdapter<CharSequence> PROC_adapter;
    ArrayAdapter<CharSequence> material_adapter;
    ArrayAdapter<CharSequence> luftversorg_adapter;
    ArrayAdapter<CharSequence> haeufigk_adapter;
    ArrayAdapter<CharSequence> staubung_adapter;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        spinnerNameList.clear();
        spinnerNameList.add("Anlage_spinner");
        spinnerNameList.add("Einheit_spinner");
        spinnerNameList.add("Verfahren_spinner");
        spinnerNameList.add("Anwendungsbereich_spinner");
        spinnerNameList.add("Produktkateg_spinner");
        spinnerNameList.add("Material_spinner");
        spinnerNameList.add("PROC_spinner");
        spinnerNameList.add("Wirkdauer_spinner");
        spinnerNameList.add("Wirkflaeche_spinner");
        spinnerNameList.add("VerwMenge_spinner");
        spinnerNameList.add("Freisetz_spinner");
        spinnerNameList.add("Luftversorg_spinner");
        spinnerNameList.add("Haeufigk_spinner");
        spinnerNameList.add("Staubung_spinner");



        
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

        wirkdauer_spinner = findViewById(R.id.Wirkdauer_spinner);
        wirkdauer_adapter = ArrayAdapter.createFromResource(this,
                R.array.wirkdauer_array, android.R.layout.simple_spinner_item);
        wirkdauer_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wirkdauer_spinner.setAdapter(wirkdauer_adapter);

        wirkflaeche_spinner = findViewById(R.id.Wirkflaeche_spinner);
        wirkflaeche_adapter = ArrayAdapter.createFromResource(this,
                R.array.wirkflaeche_array, android.R.layout.simple_spinner_item);
        wirkflaeche_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wirkflaeche_spinner.setAdapter(wirkflaeche_adapter);

        verwMenge_spinner = findViewById(R.id.VerwMenge_spinner);
        verwMenge_adapter = ArrayAdapter.createFromResource(this,
                R.array.verwMenge_array, android.R.layout.simple_spinner_item);
        verwMenge_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        verwMenge_spinner.setAdapter(verwMenge_adapter);

        freisetz_spinner = findViewById(R.id.Freisetz_spinner);
        freisetz_adapter = ArrayAdapter.createFromResource(this,
                R.array.freisetz_array, android.R.layout.simple_spinner_item);
        freisetz_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        freisetz_spinner.setAdapter(freisetz_adapter);

        anwendungsbereich_spinner = findViewById(R.id.Anwendungsbereich_spinner);
        anwendungsbereich_adapter = ArrayAdapter.createFromResource(this,
                R.array.anwendungsbereich_array, android.R.layout.simple_spinner_item);
        anwendungsbereich_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anwendungsbereich_spinner.setAdapter(anwendungsbereich_adapter);

        verfahren_spinner = findViewById(R.id.Verfahren_spinner);
        verfahren_adapter = ArrayAdapter.createFromResource(this,
                R.array.verfahren_array, android.R.layout.simple_spinner_item);
        verfahren_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        verfahren_spinner.setAdapter(verfahren_adapter);

        produktkateg_spinner = findViewById(R.id.Produktkateg_spinner);
        produktkateg_adapter = ArrayAdapter.createFromResource(this,
                R.array.produktkateg_array, android.R.layout.simple_spinner_item);
        produktkateg_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        produktkateg_spinner.setAdapter(produktkateg_adapter);

        PROC_spinner = findViewById(R.id.PROC_spinner);
        PROC_adapter = ArrayAdapter.createFromResource(this,
                R.array.PROC_array, android.R.layout.simple_spinner_item);
        PROC_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PROC_spinner.setAdapter(PROC_adapter);

        material_spinner = findViewById(R.id.Material_spinner);
        material_adapter = ArrayAdapter.createFromResource(this,
                R.array.material_array, android.R.layout.simple_spinner_item);
        material_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material_spinner.setAdapter(material_adapter);

        luftversorg_spinner = findViewById(R.id.Luftversorg_spinner);
        luftversorg_adapter = ArrayAdapter.createFromResource(this,
                R.array.luftversorg_array, android.R.layout.simple_spinner_item);
        luftversorg_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        luftversorg_spinner.setAdapter(luftversorg_adapter);

        haeufigk_spinner = findViewById(R.id.Haeufigk_spinner);
        haeufigk_adapter = ArrayAdapter.createFromResource(this,
                R.array.haeufigk_array, android.R.layout.simple_spinner_item);
        haeufigk_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        haeufigk_spinner.setAdapter(haeufigk_adapter);

        staubung_spinner = findViewById(R.id.Staubung_spinner);
        staubung_adapter = ArrayAdapter.createFromResource(this,
                R.array.staubung_array, android.R.layout.simple_spinner_item);
        staubung_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staubung_spinner.setAdapter(staubung_adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();


        Button buttonSave = (Button) findViewById(R.id.buttonSpeichernAnlage);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSpeichernAnlage:

                Spinner tempSpinner = null;
                spinnerItemsList.clear();

                for(int i = 0; i < spinnerNameList.size(); i++){
                    int resID = getResources().getIdentifier(spinnerNameList.get(i), "id", getPackageName());
                    tempSpinner = (Spinner) findViewById(resID);
                    spinnerItemsList.add(Utils.getSpinnerSelection(tempSpinner));
                }


                if (!spinnerItemsList.isEmpty()) {
                    new FormActivity.AsyncFetch(spinnerItemsList).execute();
                    return;
                }
                break;
        }
    }
    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        HttpURLConnection conn;
        URL url = null;
        List<String> spinnerElements;

        public AsyncFetch(List<String> spinnerElements) {
            this.spinnerElements = spinnerElements;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://141.45.92.216/insert_form.php");

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // ADD PARAMETER to our above url
                //TODO: here is the parameter adding point (TO URL!)
                Uri.Builder builder = new Uri.Builder();
                for(int i = 0; i < spinnerNameList.size(); i++){
                    builder.appendQueryParameter(spinnerNameList.get(i), spinnerElements.get(i));
                }
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method!!!
                    return (result.toString());

                } else {
                    return ("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) {

        }

    }

}
