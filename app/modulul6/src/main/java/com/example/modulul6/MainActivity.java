package com.example.modulul6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private Button btnAdauga, btnAfiseazaLista;

    public static ArrayList<DispozitivAudio> listaDispozitive = new ArrayList<>();

    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    DispozitivAudio dispozitiv = result.getData().getParcelableExtra("DISPOZITIV_KEY");
                    if (dispozitiv != null) {
                        listaDispozitive.add(dispozitiv);
                        tvRezultat.setText("Ultimul adăugat:\n" + dispozitiv.toString());
                        Toast.makeText(MainActivity.this, "Dispozitiv salvat în listă!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRezultat = findViewById(R.id.tvRezultat);
        btnAdauga = findViewById(R.id.btnAdauga);
        btnAfiseazaLista = findViewById(R.id.btnAfiseazaLista);

        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            addActivityLauncher.launch(intent);
        });

        btnAfiseazaLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });
    }
}