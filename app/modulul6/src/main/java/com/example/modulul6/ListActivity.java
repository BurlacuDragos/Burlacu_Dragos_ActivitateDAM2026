package com.example.modulul6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    private ListView listViewDispozitive;
    private DispozitivAdapter adapter;

    private final ActivityResultLauncher<Intent> editLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    DispozitivAudio dispozitivModificat = data.getParcelableExtra("DISPOZITIV_KEY");
                    int pozitie = data.getIntExtra("POZITIE_KEY", -1);

                    if (dispozitivModificat != null && pozitie != -1) {
                        MainActivity.listaDispozitive.set(pozitie, dispozitivModificat);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ListActivity.this, "Obiect modificat cu succes!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listViewDispozitive = findViewById(R.id.listViewDispozitive);

        adapter = new DispozitivAdapter(this, R.layout.item_dispozitiv, MainActivity.listaDispozitive);
        listViewDispozitive.setAdapter(adapter);

        listViewDispozitive.setOnItemClickListener((parent, view, position, id) -> {
            DispozitivAudio selectat = MainActivity.listaDispozitive.get(position);

            Intent intent = new Intent(ListActivity.this, AddActivity.class);
            intent.putExtra("EDIT_DISPOZITIV", selectat);
            intent.putExtra("EDIT_POZITIE", position);

            editLauncher.launch(intent);
        });

        listViewDispozitive.setOnItemLongClickListener((parent, view, position, id) -> {
            MainActivity.listaDispozitive.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ListActivity.this, "Element șters!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}