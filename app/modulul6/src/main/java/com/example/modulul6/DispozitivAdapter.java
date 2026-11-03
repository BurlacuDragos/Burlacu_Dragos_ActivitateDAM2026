package com.example.modulul6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class DispozitivAdapter extends ArrayAdapter<DispozitivAudio> {

    private final Context context;
    private final int resource;

    public DispozitivAdapter(@NonNull Context context, int resource, @NonNull List<DispozitivAudio> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        DispozitivAudio dispozitiv = getItem(position);

        if (dispozitiv != null) {
            TextView tvTitlu = convertView.findViewById(R.id.tvItemTitlu);
            TextView tvPret = convertView.findViewById(R.id.tvItemPret);
            TextView tvDetalii = convertView.findViewById(R.id.tvItemDetalii);

            tvTitlu.setText(dispozitiv.getDenumire() + " (" + dispozitiv.getMarca() + ")");
            tvPret.setText("Preț: " + dispozitiv.getPret() + " RON");
            tvDetalii.setText("BT: " + (dispozitiv.isBluetooth() ? "Da" : "Nu") + " | " + dispozitiv.getTip());
        }

        return convertView;
    }
}