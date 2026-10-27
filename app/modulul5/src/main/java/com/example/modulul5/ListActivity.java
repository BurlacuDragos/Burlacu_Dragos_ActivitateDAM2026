package com.example.modulul5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    private ListView listViewDispozitive;
    private ArrayAdapter<DispozitivAudio> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listViewDispozitive = findViewById(R.id.listViewDispozitive);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.listaDispozitive
        );
        listViewDispozitive.setAdapter(adapter);

        listViewDispozitive.setOnItemClickListener((parent, view, position, id) -> {
            DispozitivAudio dispozitiv = MainActivity.listaDispozitive.get(position);
            Toast.makeText(ListActivity.this, dispozitiv.toString(), Toast.LENGTH_LONG).show();
        });

        listViewDispozitive.setOnItemLongClickListener((parent, view, position, id) -> {
            MainActivity.listaDispozitive.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ListActivity.this, "Element șters cu succes!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}