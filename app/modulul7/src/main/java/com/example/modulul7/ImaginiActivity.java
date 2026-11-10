package com.example.modulul7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ImaginiActivity extends AppCompatActivity {

    private ListView lvImagini;
    private List<ItemImagine> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagini);

        lvImagini = findViewById(R.id.lvImagini);

        incarcaDate();

        ImagineAdapter adapter = new ImagineAdapter(this, R.layout.item_imagine, lista);
        lvImagini.setAdapter(adapter);

        lvImagini.setOnItemClickListener((parent, view, position, id) -> {
            ItemImagine selectat = lista.get(position);
            Intent intent = new Intent(ImaginiActivity.this, WebViewActivity.class);
            intent.putExtra("URL_WEB", selectat.getUrlWeb());
            startActivity(intent);
        });
    }

    private void incarcaDate() {
        lista.add(new ItemImagine("Sony WH-1000XM5 Căști Over-Ear",
                "https://upload.wikimedia.org/wikipedia/commons/6/6f/Sony_WH-1000XM4.jpg",
                "https://www.sony.ro/headphones/products/wh-1000xm5"));

        lista.add(new ItemImagine("JBL Flip 6 Boxă Portabilă",
                "https://upload.wikimedia.org/wikipedia/commons/a/a2/JBL_Flip_4_Speaker.jpg",
                "https://ro.jbl.com/boxe-portabile/FLIP-6-.html"));

        lista.add(new ItemImagine("Bose QuietComfort Earbuds II",
                "https://upload.wikimedia.org/wikipedia/commons/0/02/Bose_QuietComfort_35_II_black.jpg",
                "https://www.bose.ro"));

        lista.add(new ItemImagine("Sennheiser HD 600 Căști Pro",
                "https://upload.wikimedia.org/wikipedia/commons/c/c6/Sennheiser_HD_650_Headphones.jpg",
                "https://www.sennheiser-hearing.com/ro-RO/p/hd-600"));

        lista.add(new ItemImagine("Apple AirPods Max",
                "https://upload.wikimedia.org/wikipedia/commons/0/00/AirPods_Max_Space_Gray.png",
                "https://www.apple.com/ro/airpods-max"));
    }
}