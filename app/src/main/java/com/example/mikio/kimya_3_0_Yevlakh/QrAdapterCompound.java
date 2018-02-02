package com.example.mikio.kimya_3_0_Yevlakh;


import android.content.Context;
import android.content.Intent;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Collections;
import java.util.List;

public class QrAdapterCompound extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataCompound> data = Collections.emptyList();

    // create constructor to initialize context and data sent from MainActivity
    public QrAdapterCompound(Context context, List<DataCompound> data) {
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
                    String substanceCas = dataCompound.cas;
                    if (substanceCas != null && !substanceCas.isEmpty()) {
                        final Intent iG;
                        iG = new Intent(context, QrGenerate.class);
                        String substanceIdString = String.valueOf(dataCompound.id);
                        iG.putExtra("substanceCas", dataCompound.cas);
                        iG.putExtra("substanceName", dataCompound.name);
                        iG.putExtra("substanceID", substanceIdString);

                        context.startActivity(iG);
                    }
                }
            }
            Toast.makeText(clickedRow.getContext(), "Im Suchergebnis war keine URL enthalten :(", Toast.LENGTH_LONG).show();
        }
    }
}



