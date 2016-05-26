package id.ac.unsyiah.elektro.mobile.databasebarang;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import id.ac.unsyiah.elektro.mobile.databasebarang.model.BarangDB;

/**
 * Created by Alchusuvie on 5/26/2016.
 */
public class TambahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
    }

public void onClickBtnSimpanTambah (View view) {
    TextView txtSku = (TextView) findViewById(R.id.baruSku);
    String sku = txtSku.getText().toString();

    TextView txtNama = (TextView) findViewById(R.id.baruNama);
    String nama = txtNama.getText().toString();

    TextView txtStok = (TextView) findViewById(R.id.baruStok);
    String stok = txtStok.getText().toString();

    TextView txtSatuan = (TextView) findViewById(R.id.baruSatuan);
    String satuan = txtSatuan.getText().toString();

    TextView txtGambar = (TextView) findViewById(R.id.baruGambar);
    String gambar = txtGambar.getText().toString();


    SQLiteOpenHelper barangDB = new BarangDB(this);
    SQLiteDatabase db = barangDB.getWritableDatabase();
    // Sebutkan nilai baru yang akan ditambahkan
    ContentValues barangBaru = new ContentValues();
    barangBaru.put(BarangDB.BARANG_SKU, sku);
    barangBaru.put(BarangDB.BARANG_NAMA, nama);
    barangBaru.put(BarangDB.BARANG_STOK, stok);
    barangBaru.put(BarangDB.BARANG_SATUAN, satuan);
    barangBaru.put(BarangDB.BARANG_GAMBAR, gambar);

    // Tambahkan ke database
    db.insert(BarangDB.TABEL_BARANG, null, barangBaru);
    // Tutup koneksi ke database
    db.close();

    // Kirim kembali ke Activity MainActivity
    Intent pesan = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(pesan);
    finish();
}
}
