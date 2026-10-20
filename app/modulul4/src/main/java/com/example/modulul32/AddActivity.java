package com.example.modulul32;


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
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText etDenumire, etPret;
    private CheckBox cbBluetooth;
    private RadioGroup rgTip;
    private Spinner spMarca;
    private RatingBar ratingBar;
    private Switch swGarantie;
    private Button btnSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etDenumire = findViewById(R.id.etDenumire);
        etPret = findViewById(R.id.etPret);
        cbBluetooth = findViewById(R.id.cbBluetooth);
        rgTip = findViewById(R.id.rgTip);
        spMarca = findViewById(R.id.spMarca);
        ratingBar = findViewById(R.id.ratingBar);
        swGarantie = findViewById(R.id.swGarantie);
        btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] marci = {"Sony", "JBL", "Bose", "Sennheiser"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, marci);
        spMarca.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {
            String denumire = etDenumire.getText().toString();
            float pret = etPret.getText().toString().isEmpty() ? 0.0f : Float.parseFloat(etPret.getText().toString());
            boolean esteBluetooth = cbBluetooth.isChecked();

            int selectedTipId = rgTip.getCheckedRadioButtonId();
            RadioButton rbSelected = findViewById(selectedTipId);
            String tipDispozitiv = rbSelected != null ? rbSelected.getText().toString() : "Nespecificat";

            String marca = spMarca.getSelectedItem().toString();
            float rating = ratingBar.getRating();
            boolean areGarantie = swGarantie.isChecked();

            DispozitivAudio dispozitiv = new DispozitivAudio(denumire, pret, esteBluetooth, tipDispozitiv, marca, rating, areGarantie);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("DISPOZITIV_KEY", dispozitiv);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}