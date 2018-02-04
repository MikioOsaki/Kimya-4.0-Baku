package com.example.mikio.kimya_3_0_Yevlakh;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.List;

public class AdapterCompound extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private Context context;
    private LayoutInflater inflater;
    List<DataCompound> data = Collections.emptyList();

    // create constructor to initialize context and data sent from MainActivity
    public AdapterCompound(Context context, List<DataCompound> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_compound, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        DataCompound current = data.get(position);
        myHolder.textCompoundName.setText(current.name);
        myHolder.textEGNR.setText("EG-NR: " + current.eg);
        myHolder.textCASNR.setText("CAS-NR: " + current.cas);
        myHolder.textID.setText("Stoff ID " + current.id);
        myHolder.textID.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        myHolder.rootView.setTag(position);
        myHolder.rootView.setOnClickListener(myHolder);
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View rootView;
        private TextView textCompoundName;
        private TextView textEGNR;
        private TextView textCASNR;
        private TextView textID;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            textCompoundName = (TextView) itemView.findViewById(R.id.textCompoundName);
            textEGNR = (TextView) itemView.findViewById(R.id.textEGNR);
            textCASNR = (TextView) itemView.findViewById(R.id.textCASNR);
            textID = (TextView) itemView.findViewById(R.id.textID);
        }

        // Click event for all items
        @Override
        public void onClick(View clickedRow) {
            int position = (int) clickedRow.getTag();
            if (data != null && data.size() >= position) {
                DataCompound dataCompound = data.get(position);
                if (dataCompound != null) {
                    int substanceId = dataCompound.id;
                    String substanceIdString = String.valueOf(substanceId);
                    if (substanceIdString != null && !substanceIdString.isEmpty()) {
                        new AdapterCompound.AsyncFetch(substanceIdString).execute();
                        return;
                    }
                }
            }
            Toast.makeText(clickedRow.getContext(), "Im Suchergebnis war keine URL enthalten :(", Toast.LENGTH_LONG).show();
        }
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        HttpURLConnection conn;
        URL url = null;
        String substance_id;

        public AsyncFetch(String substance_id) {
            this.substance_id = substance_id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://141.45.92.216/pdf_download.php");

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

                // add parameter to our above reach_nr
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("substance_id", substance_id);
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

            Utils.openBrowser(context, result);
        }

    }
}
