package id.ac.unsyiah.elektro.mobile.databasebarang.model;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import id.ac.unsyiah.elektro.mobile.databasebarang.R;

/**
 * Created by Alchusuvie on 5/26/2016.
 */
public class BarangCursorAdapter extends CursorAdapter {
    public BarangCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Pakai layout model.xml
        return LayoutInflater.from(context).inflate(R.layout.model, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Ambil data dari query dan tempatkan ke elemen-elemen tersebut di layout, yang diminta hanya 3, yaitu sku,nama dan gambar
        String nim = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_SKU));
        TextView txtNIM = (TextView) view.findViewById(R.id.modelSku);
        txtNIM.setText(nim);

        String nama = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_NAMA));
        TextView txtNama = (TextView) view.findViewById(R.id.modelNama);
        txtNama.setText(nama);

        String gambar = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_GAMBAR)); //ambil nama yang dimasukkan
        int resId = context.getResources().getIdentifier(gambar, "drawable",context.getPackageName()); //nama gambar itu ada dalam folder drawable, directorynya disimpan dalam resID
        ImageView listGambar = (ImageView) view.findViewById(R.id.modelGambar);
        listGambar.setImageResource(resId); //lokasi gambar diset
    }
}
