package id.ac.unsyiah.elektro.mobile.databasebarang;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.ac.unsyiah.elektro.mobile.databasebarang.model.BarangDB;

/**
 * Created by Alchusuvie on 5/26/2016.
 */
public class RincianActivity extends AppCompatActivity {
    private long id;
    private String sku;
    private String nama;
    private String stok;
    private String satuan;
    private String gambar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian);

        Intent pesan = getIntent();
        id = pesan.getLongExtra("id", 0);

        SQLiteOpenHelper barangDB = new BarangDB(this);
        SQLiteDatabase db = barangDB.getReadableDatabase();

        Cursor cursor = db.query(BarangDB.TABEL_BARANG,                        // FROM
                new String[]{BarangDB.BARANG_SKU, BarangDB.BARANG_NAMA, BarangDB.BARANG_STOK, BarangDB.BARANG_SATUAN, BarangDB.BARANG_GAMBAR},
                BarangDB.BARANG_ID + "=" + Long.toString(id), // WHERE
                null,
                null,                                                   // GROUP BY
                null,                                                   // HAVING
                null);                                                  // ORDER BY
        cursor.moveToFirst();

        sku = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_SKU));
        nama = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_NAMA));
        stok = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_STOK));
        satuan = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_SATUAN));
        gambar = cursor.getString(cursor.getColumnIndexOrThrow(BarangDB.BARANG_GAMBAR));

        cursor.close();
        // Tutup database
        db.close();

        EditText txtSku = (EditText) findViewById(R.id.rincianSku);
        txtSku.setText(sku);

        EditText txtNama = (EditText) findViewById(R.id.rincianNama);
        txtNama.setText(nama);

        EditText txtStok = (EditText) findViewById(R.id.rincianStok);
        txtStok.setText(stok);

        EditText txtSatuan = (EditText) findViewById(R.id.rincianSatuan);
        txtSatuan.setText(satuan);

        EditText txtGambar = (EditText) findViewById(R.id.rincianGambar);
        txtGambar.setText(gambar);

    }

    public void clickBtnUbah(View view) {

        EditText txtSku = (EditText) findViewById(R.id.rincianSku);
        txtSku.setEnabled(true);

        EditText txtNama = (EditText) findViewById(R.id.rincianNama);
        txtNama.setEnabled(true);

        EditText txtStok = (EditText) findViewById(R.id.rincianStok);
        txtStok.setEnabled(true);

        EditText txtSatuan = (EditText) findViewById(R.id.rincianSatuan);
        txtSatuan.setEnabled(true);

        EditText txtGambar = (EditText) findViewById(R.id.rincianGambar);
        txtGambar.setEnabled(true);

        // Hilangkan tombol Hapus
        Button btnHapus = (Button) findViewById(R.id.btnHapus);
        btnHapus.setVisibility(View.GONE);
        // Hilangkan tombol Ubah
        Button btnUbah = (Button) findViewById(R.id.btnUbah);
        btnUbah.setVisibility(View.GONE);
        // Tampilkan tombol Simpan, untuk menyimpan perubahan
        Button btnSimpan = (Button) findViewById(R.id.btnSimpanUbah);
        btnSimpan.setVisibility(View.VISIBLE);
    }



    public void clickBtnHapus(View view) {
        // Tampilkan DialogBox menanyakan apa benar mau dihapus
        new AlertDialog.Builder(this)
                // Judul dialog box
                .setTitle("Hapus?")
                        // Pesan dalam dialog box
                .setMessage("Yakin ingin menghapus item ini?")
                        // Tombol untuk Ya akan dihapus
                .setPositiveButton("Ya", // Tulisan pada tombol
                        // Listener untuk tangani penekanan tombol ini
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                clickBtnYaHapus(dialog, which);
                            }
                        })
                .setNegativeButton("Tidak", // Tulisan pada tombol
                        // Listener untuk tangani penekanan tombol ini
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // Tidak melakukan apapun
                            }
                        })
                        // Muncul Icon hati-hati pada dialog box
                .setIcon(android.R.drawable.ic_dialog_alert)
                        // Tampilkan dialog box
                .show();
    }

    private void clickBtnYaHapus(DialogInterface dialog, int which) {
        // Hapus data di database
        SQLiteOpenHelper barangDB = new BarangDB(this);
        SQLiteDatabase db = barangDB.getWritableDatabase();
        // Jalan SQL Delete
        db.delete(BarangDB.TABEL_BARANG,                      // Tabel yang mau dihapus
                BarangDB.BARANG_ID + "=" + Long.toString(id), // WHERE
                null);                                                  // Argumen WHERE
        // Tutup database
        db.close();

        // Kembali ke Activity MainActivity
        Intent pesan = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(pesan);
        finish();
    }


    public void clickBtnSimpanUbah(View view) {
        // Ambil NIM yang baru diubah
        EditText txtSku = (EditText) findViewById(R.id.rincianSku);
        String skuBaru = txtSku.getText().toString();

        EditText txtNama = (EditText) findViewById(R.id.rincianNama);
        String namaBaru = txtNama.getText().toString();

        EditText txtStok = (EditText) findViewById(R.id.rincianStok);
        String stokBaru = txtStok.getText().toString();

        EditText txtSatuan = (EditText) findViewById(R.id.rincianSatuan);
        String satuanBaru = txtSku.getText().toString();

        EditText txtGambar = (EditText) findViewById(R.id.rincianGambar);
        String gambarBaru = txtGambar.getText().toString();


        // Ubah data di database
        SQLiteOpenHelper barangDB = new BarangDB(this);
        SQLiteDatabase db = barangDB.getWritableDatabase();
        // Tentukan nilai-nilai baru
        ContentValues barangBaru = new ContentValues();
        barangBaru.put(BarangDB.BARANG_SKU, skuBaru);
        barangBaru.put(BarangDB.BARANG_NAMA, namaBaru);
        barangBaru.put(BarangDB.BARANG_STOK, stokBaru);
        barangBaru.put(BarangDB.BARANG_SATUAN, satuanBaru);
        barangBaru.put(BarangDB.BARANG_GAMBAR, gambarBaru);


        // Jalan SQL Update
        db.update(BarangDB.TABEL_BARANG,                        // Tabel yang mau diubah
                barangBaru,                                          // Nilai barunya
                BarangDB.BARANG_ID + "=" + Long.toString(id), // WHERE
                null);                                                  // Argumen WHERE
        // Tutup database
        db.close();

        // Kembali ke Activity MainActivity
        Intent pesan = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(pesan);
        finish();
    }
}