package com.example.modulul7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText etDenumire, etPret;
    private CheckBox cbBluetooth, cbGarantie;
    private RadioGroup rgTip;
    private Spinner spinnerMarca;
    private RatingBar ratingBar;
    private Button btnSalveaza;

    private int pozitieModificare = -1;
    private final String[] marci = new String[]{"Sony", "JBL", "Bose", "Sennheiser", "Apple", "Samsung"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etDenumire = findViewById(R.id.etDenumire);
        etPret = findViewById(R.id.etPret);
        cbBluetooth = findViewById(R.id.cbBluetooth);
        cbGarantie = findViewById(R.id.cbGarantie);
        rgTip = findViewById(R.id.rgTip);
        spinnerMarca = findViewById(R.id.spinnerMarca);
        ratingBar = findViewById(R.id.ratingBar);
        btnSalveaza = findViewById(R.id.btnSalveaza);

        ArrayAdapter<String> adapterMarca = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, marci);
        spinnerMarca.setAdapter(adapterMarca);

        Intent intentIn = getIntent();
        if (intentIn != null && intentIn.hasExtra("EDIT_DISPOZITIV")) {
            DispozitivAudio deEditat = intentIn.getParcelableExtra("EDIT_DISPOZITIV");
            pozitieModificare = intentIn.getIntExtra("EDIT_POZITIE", -1);

            if (deEditat != null) {
                etDenumire.setText(deEditat.getDenumire());
                etPret.setText(String.valueOf(deEditat.getPret()));
                cbBluetooth.setChecked(deEditat.isBluetooth());
                cbGarantie.setChecked(deEditat.isGarantie());
                ratingBar.setRating(deEditat.getRating());

                if (deEditat.getMarca() != null) {
                    for (int i = 0; i < marci.length; i++) {
                        if (marci[i].equalsIgnoreCase(deEditat.getMarca())) {
                            spinnerMarca.setSelection(i);
                            break;
                        }
                    }
                }
            }
        }

        btnSalveaza.setOnClickListener(v -> {
            String denumire = etDenumire.getText().toString().trim();
            String pretStr = etPret.getText().toString().trim();

            if (denumire.isEmpty() || pretStr.isEmpty()) {
                Toast.makeText(this, "Completați denumirea și prețul!", Toast.LENGTH_SHORT).show();
                return;
            }

            float pret;
            try {
                pret = Float.parseFloat(pretStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Introduceți un preț valid!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean bluetooth = cbBluetooth.isChecked();
            boolean garantie = cbGarantie.isChecked();
            float rating = ratingBar.getRating();

            int selectedRadioId = rgTip.getCheckedRadioButtonId();
            String tip = "Nespecificat";
            if (selectedRadioId != -1) {
                RadioButton rb = findViewById(selectedRadioId);
                if (rb != null) tip = rb.getText().toString();
            }

            String marca = spinnerMarca.getSelectedItem() != null ? spinnerMarca.getSelectedItem().toString() : marci[0];

            DispozitivAudio dispozitivNou = new DispozitivAudio(denumire, pret, bluetooth, tip, marca, rating, garantie);

            Intent intentOut = new Intent();
            intentOut.putExtra("DISPOZITIV_KEY", dispozitivNou);
            if (pozitieModificare != -1) {
                intentOut.putExtra("POZITIE_KEY", pozitieModificare);
            }
            setResult(RESULT_OK, intentOut);
            finish();
        });
    }
}