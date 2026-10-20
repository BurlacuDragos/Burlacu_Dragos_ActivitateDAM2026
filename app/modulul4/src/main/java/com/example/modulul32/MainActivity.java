package com.example.modulul32;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private Button btnAdauga;

    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        DispozitivAudio dispozitiv = (DispozitivAudio) bundle.getSerializable("DISPOZITIV_KEY");
                        if (dispozitiv != null) {
                            tvRezultat.setText(dispozitiv.toString());
                            Toast.makeText(MainActivity.this, "Dispozitiv adăugat:\n" + dispozitiv.getDenumire(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
    );

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvRezultat = findViewById(R.id.tvRezultat);
        btnAdauga = findViewById(R.id.btnAdauga);

        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });
    }
}