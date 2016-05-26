package id.ac.unsyiah.elektro.mobile.databasebarang.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alchusuvie on 5/26/2016.
 */
public class BarangDB extends SQLiteOpenHelper {
    // Versi DB
    public static final int VERSI_DB = 1;
    // Nama DB
    public static final String NAMA_DB = "Barang";
    // Nama Table
    public static final String TABEL_BARANG = "BARANG";
    // Kolom DB
    public static final String BARANG_ID = "_id";
    public static final String BARANG_SKU = "SKU";
    public static final String BARANG_NAMA = "nama";
    public static final String BARANG_STOK = "stok";
    public static final String BARANG_SATUAN = "satuan";
    public static final String BARANG_GAMBAR = "gambar";

    public BarangDB(Context context) {
        super(context, NAMA_DB, null, VERSI_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Perintah untuk buat DB baru jika belum ada
        final String buatDB = "CREATE TABLE IF NOT EXISTS " + TABEL_BARANG + " ( "
                + BARANG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BARANG_SKU +" TEXT, "
                + BARANG_NAMA + " TEXT, "
                + BARANG_STOK + " TEXT, "
                + BARANG_SATUAN + " TEXT, "
                + BARANG_GAMBAR + " TEXT "
                + ");";
        // Jalankan perintah buat DB
        db.execSQL(buatDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
    }
}