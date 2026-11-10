package com.example.modulul7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImagineAdapter extends ArrayAdapter<ItemImagine> {

    private final Context context;
    private final int resource;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public ImagineAdapter(@NonNull Context context, int resource, @NonNull List<ItemImagine> objects) {
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

        ImageView ivItem = convertView.findViewById(R.id.ivItem);
        TextView tvItemDescriere = convertView.findViewById(R.id.tvItemDescriere);

        ItemImagine item = getItem(position);

        if (item != null) {
            tvItemDescriere.setText(item.getDescriere());
            ivItem.setImageBitmap(null);

            executor.execute(() -> {
                try {
                    URL url = new URL(item.getUrlImagine());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
                    conn.setDoInput(true);
                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == 307 || responseCode == 308) {
                        String redirectUrl = conn.getHeaderField("Location");
                        conn = (HttpURLConnection) new URL(redirectUrl).openConnection();
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
                        conn.connect();
                    }

                    InputStream in = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    handler.post(() -> ivItem.setImageBitmap(bitmap));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        return convertView;
    }
}