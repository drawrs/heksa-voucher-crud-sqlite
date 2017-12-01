package com.khilman.www.heksavoucher.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khilman.www.heksavoucher.model.PolisModel;
import com.khilman.www.heksavoucher.model.VoucherModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/6/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_voucher_rx";
    private static final int DATABASE_VERSION = 2;

    // table name
    private static final String TMP_POLIS_TABLE = "tmp_polis";
    private static final String TB_VOUCHER = "tb_voucher";

    // tmp_polis table column
    private static final String TMP_ID = "_id";
    private static final String TMP_NO_POLIS = "no_polis";
    private static final String TMP_NILAI_PREMI = "nilai_premi";
    private static final String TMP_NILAI_PERTANGGUNGAN = "nilai_pertanggungan";
    private static final String TMP_TGL_MULAI = "tgl_mulai";
    private static final String TMP_TGL_AKHIR = "tgl_akhir";

    // tb_voucher
    private static final String VC_ID = "_id";
    private static final String VC_NAMA = "nama_u";
    private static final String VC_JK = "jk_u";
    private static final String VC_TGL_LAHIR = "tgl_lahir_u";
    private static final String VC_USIA = "usia_u";
    private static final String VC_KTP = "ktp_u";
    private static final String VC_TELP = "telp_u";
    private static final String VC_AHLI_WARIS = "ahli_waris_u";
    private static final String VC_HUBUNGAN = "hubungan_u";
    private static final String VC_REFERAL = "referal";
    private static final String VC_KODE_POS = "kode_pos_u";
    private static final String VC_PROV = "prov_u";
    private static final String VC_KAB = "kab_u";
    private static final String VC_KEC = "kec_u";
    private static final String VC_FOTO_NS = "foto_ns";
    private static final String VC_FOTO_KTP = "foto_ktp";
    private static final String VC_FOTO_TKD = "foto_tkd";
    private static final String VC_FOTO_TKB = "foto_tkb";


    private static final String CREATE_TABLE_TMP_POLIS = "CREATE TABLE "+TMP_POLIS_TABLE+" " +
                                                            "("+TMP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                            ""+TMP_NO_POLIS+" varchar(100), " +
                                                            ""+TMP_NILAI_PREMI+" BIGINT(100), " +
                                                            ""+TMP_NILAI_PERTANGGUNGAN+" BIGINT(100), " +
                                                            ""+TMP_TGL_MULAI+" DATE, " +
                                                            ""+TMP_TGL_AKHIR+" date)";

    private static final String CREATE_TABLE_TB_VOUCHER = "CREATE TABLE "+TB_VOUCHER+" " +
                                                            "("+VC_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                            ""+TMP_NO_POLIS+" varchar(100), " +
                                                            ""+TMP_NILAI_PREMI+" BIGINT(100), " +
                                                            ""+TMP_NILAI_PERTANGGUNGAN+" BIGINT(100), " +
                                                            ""+TMP_TGL_MULAI+" DATE, " +
                                                            ""+TMP_TGL_AKHIR+" date," +
                                                            ""+VC_NAMA+" VARCHAR(100),\n" +
                                                            ""+VC_JK+" VARCHAR(1), " +
                                                            ""+VC_TGL_LAHIR+" DATE, " +
                                                            ""+VC_USIA+" VARCHAR(5), " +
                                                            ""+VC_KTP+" VARCHAR(100), " +
                                                            ""+VC_TELP+" VARCHAR(100), " +
                                                            ""+VC_AHLI_WARIS+" VARCHAR(100), " +
                                                            ""+VC_HUBUNGAN+" VARCHAR(50), " +
                                                            ""+VC_REFERAL+" VARCHAR(100), " +
                                                            ""+VC_KODE_POS+" VARCHAR(100), " +
                                                            ""+VC_PROV+" VARCHAR(100), " +
                                                            ""+VC_KAB+" VARCHAR(100), " +
                                                            ""+VC_KEC+" VARCHAR(100), " +
                                                            ""+VC_FOTO_NS+" VARCHAR(100), " +
                                                            ""+VC_FOTO_KTP+" VARCHAR(100), " +
                                                            ""+VC_FOTO_TKD+" VARCHAR(100), " +
                                                            ""+VC_FOTO_TKB+" VARCHAR(100))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Query create table tmp_p
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TMP_POLIS);
        db.execSQL(CREATE_TABLE_TB_VOUCHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TMP_POLIS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TB_VOUCHER);
        onCreate(db);
    }

    public long insert_tmp_polis(String no_polis, String nilai_premi, String nilai_pertanggungan, String tgl_mulai, String tgl_akhir){
        // tmp_polis table column
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // put data into cv
        cv.put(TMP_NO_POLIS, no_polis);
        cv.put(TMP_NILAI_PREMI, nilai_premi);
        cv.put(TMP_NILAI_PERTANGGUNGAN, nilai_pertanggungan);
        cv.put(TMP_TGL_MULAI, tgl_mulai);
        cv.put(TMP_TGL_AKHIR, tgl_akhir);

        long polis_id = db.insert(TMP_POLIS_TABLE, null, cv);
        return polis_id;
    }
    // tb_voucher
    public long insert_tb_voucher(String no_polis,
                                  String nilai_premi,
                                  String nilai_pertanggungan,
                                  String tgl_mulai,
                                  String tgl_akhir,
                                  String nama_u,
                                  String jk_u,
                                  String tgl_lahir_u,
                                  String usia_u,
                                  String ktp_u,
                                  String telp_u,
                                  String ahli_waris_u,
                                  String hubungan_u,
                                  String referal_u,
                                  String kode_pos_u,
                                  String prov_u,
                                  String kab_u,
                                  String kec_u,
                                  String foto_ns,
                                  String foto_ktp,
                                  String foto_tkd,
                                  String foto_tkb){
        // tmp_polis table column
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // put data into cv
        cv.put(TMP_NO_POLIS, no_polis);
        cv.put(TMP_NILAI_PREMI, nilai_premi);
        cv.put(TMP_NILAI_PERTANGGUNGAN, nilai_pertanggungan);
        cv.put(TMP_TGL_MULAI, tgl_mulai);
        cv.put(TMP_TGL_AKHIR, tgl_akhir);
        cv.put(VC_NAMA, nama_u);
        cv.put(VC_JK, jk_u);
        cv.put(VC_TGL_LAHIR, tgl_lahir_u);
        cv.put(VC_USIA, usia_u);
        cv.put(VC_KTP, ktp_u);
        cv.put(VC_TELP, telp_u);
        cv.put(VC_AHLI_WARIS, ahli_waris_u);
        cv.put(VC_HUBUNGAN, hubungan_u);
        cv.put(VC_REFERAL, referal_u);
        cv.put(VC_KODE_POS, kode_pos_u);
        cv.put(VC_PROV, prov_u);
        cv.put(VC_KAB, kab_u);
        cv.put(VC_KEC, kec_u);
        cv.put(VC_FOTO_NS, foto_ns);
        cv.put(VC_FOTO_KTP, foto_ktp);
        cv.put(VC_FOTO_TKD, foto_tkd);
        cv.put(VC_FOTO_TKB, foto_tkb);


        // (table tujuan, null, data yg dimasukan)
        long polis_id = db.insert(TB_VOUCHER, null, cv);
        return polis_id;
    }

    public List<PolisModel> getDataPolis(String no_polis){
        List<PolisModel> listPolis = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // definisikan setiap kolom yang akan diambil
        String[] columnName = {VC_ID,
                                TMP_NO_POLIS,
                                TMP_NILAI_PREMI,
                                TMP_NILAI_PERTANGGUNGAN,
                                TMP_TGL_MULAI,
                                TMP_TGL_AKHIR};

        Cursor cursor = db.query(TMP_POLIS_TABLE, columnName, TMP_NO_POLIS+"=?", new String[]{ no_polis }, null, null, null);
        while (cursor.moveToNext()){
            if (cursor != null){
                String nilai_premi = cursor.getString(cursor.getColumnIndex(TMP_NILAI_PREMI));
                String nilai_pertanggungan = cursor.getString(cursor.getColumnIndex(TMP_NILAI_PERTANGGUNGAN));
                String tgl_mulai = cursor.getString(cursor.getColumnIndex(TMP_TGL_MULAI));
                String tgl_akhir = cursor.getString(cursor.getColumnIndex(TMP_TGL_AKHIR));
                //String no_polis = cursor.getString(cursor.getColumnIndex(TMP_NO_POLIS));

                PolisModel polis = new PolisModel();
                polis.setNilai_premi(nilai_premi);
                polis.setNilai_pertanggungan(nilai_pertanggungan);
                polis.setTgl_mulai(tgl_mulai);
                polis.setTgl_akhir(tgl_akhir);
                polis.setNo_polis(no_polis);
                // add ke model
                listPolis.add(polis);
            }
        }
        db.close();
        return listPolis;
    }
    public List<VoucherModel> getDataVoucher(){
        List<VoucherModel> listVoucher = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // definisikan setiap kolom yang akan diambil
        String[] columnName = {VC_ID,
                TMP_NO_POLIS,
                TMP_NILAI_PREMI,
                TMP_NILAI_PERTANGGUNGAN,
                TMP_TGL_MULAI,
                TMP_TGL_AKHIR,
                TMP_NO_POLIS,
                TMP_NILAI_PREMI,
                TMP_NILAI_PERTANGGUNGAN,
                TMP_TGL_MULAI,
                TMP_TGL_AKHIR,
                VC_NAMA,
                VC_JK,
                VC_TGL_LAHIR,
                VC_USIA,
                VC_KTP,
                VC_TELP,
                VC_AHLI_WARIS,
                VC_HUBUNGAN,
                VC_REFERAL,
                VC_KODE_POS,
                VC_PROV,
                VC_KAB,
                VC_KEC,
                VC_FOTO_NS,
                VC_FOTO_KTP,
                VC_FOTO_TKD,
                VC_FOTO_TKB};
        Cursor cursor = db.query(TB_VOUCHER, columnName, null, null, null, null, VC_ID + " DESC");
        while (cursor.moveToNext()){
            if (cursor != null){
                String nilai_premi = cursor.getString(cursor.getColumnIndex(TMP_NILAI_PREMI));
                String nilai_pertanggungan = cursor.getString(cursor.getColumnIndex(TMP_NILAI_PERTANGGUNGAN));
                String tgl_mulai = cursor.getString(cursor.getColumnIndex(TMP_TGL_MULAI));
                String tgl_akhir = cursor.getString(cursor.getColumnIndex(TMP_TGL_AKHIR));
                String no_polis = cursor.getString(cursor.getColumnIndex(TMP_NO_POLIS));

                //
                String vc_id = cursor.getString(cursor.getColumnIndex(VC_ID));
                String nama_u = cursor.getString(cursor.getColumnIndex(VC_NAMA));
                String jk_u = cursor.getString(cursor.getColumnIndex(VC_JK));
                String tgl_lahir_u = cursor.getString(cursor.getColumnIndex(VC_TGL_LAHIR));
                String usia_u = cursor.getString(cursor.getColumnIndex(VC_USIA));
                String ktp_u = cursor.getString(cursor.getColumnIndex(VC_KTP));
                String telp_u = cursor.getString(cursor.getColumnIndex(VC_TELP));
                String ahli_waris_u = cursor.getString(cursor.getColumnIndex(VC_AHLI_WARIS));
                String hubungan_u = cursor.getString(cursor.getColumnIndex(VC_HUBUNGAN));
                String referal_u = cursor.getString(cursor.getColumnIndex(VC_REFERAL));
                String kode_pos_u = cursor.getString(cursor.getColumnIndex(VC_KODE_POS));
                String prov_u = cursor.getString(cursor.getColumnIndex(VC_PROV));
                String kab_u = cursor.getString(cursor.getColumnIndex(VC_KAB));
                String kec_u = cursor.getString(cursor.getColumnIndex(VC_KEC));
                String foto_ns = cursor.getString(cursor.getColumnIndex(VC_FOTO_NS));
                String foto_ktp = cursor.getString(cursor.getColumnIndex(VC_FOTO_KTP));
                String foto_tkd = cursor.getString(cursor.getColumnIndex(VC_FOTO_TKD));
                String foto_tkb = cursor.getString(cursor.getColumnIndex(VC_FOTO_TKB));

                VoucherModel voucherModel = new VoucherModel();
                voucherModel.set_id(vc_id);
                voucherModel.setNilai_premi(nilai_premi);
                voucherModel.setNilai_pertanggungan(nilai_pertanggungan);
                voucherModel.setTgl_mulai(tgl_mulai);
                voucherModel.setTgl_akhir(tgl_akhir);
                voucherModel.setNo_polis(no_polis);
                // set data voucher
                voucherModel.setNama_u(nama_u);
                voucherModel.setJk_u(jk_u);
                voucherModel.setTgl_lahir_u(tgl_lahir_u);
                voucherModel.setUsia_u(usia_u);
                voucherModel.setKtp_u(ktp_u);
                voucherModel.setTelp_u(telp_u);
                voucherModel.setAhli_waris_u(ahli_waris_u);
                voucherModel.setHubungan_u(hubungan_u);
                voucherModel.setReferal_u(referal_u);
                voucherModel.setKode_pos_u(kode_pos_u);
                voucherModel.setProv_u(prov_u);
                voucherModel.setKab_u(kab_u);
                voucherModel.setKec_u(kec_u);
                voucherModel.setFoto_ns(foto_ns);
                voucherModel.setFoto_ktp(foto_ktp);
                voucherModel.setFoto_tkd(foto_tkd);
                voucherModel.setFoto_tkb(foto_tkb);
                // add ke model
                listVoucher.add(voucherModel);
            }
        }
        return listVoucher;
    }
    public boolean deleteVoucher(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TB_VOUCHER, VC_ID + "=" + id, null) > 0;
    }
    public int updateVoucher(String id,
                             String no_polis,
                             String nilai_premi,
                             String nilai_pertanggungan,
                             String tgl_mulai,
                             String tgl_akhir,
                             String nama_u,
                             String jk_u,
                             String tgl_lahir_u,
                             String usia_u,
                             String ktp_u,
                             String telp_u,
                             String ahli_waris_u,
                             String hubungan_u,
                             String referal_u,
                             String kode_pos_u,
                             String prov_u,
                             String kab_u,
                             String kec_u,
                             String foto_ns,
                             String foto_ktp,
                             String foto_tkd,
                             String foto_tkb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TMP_NO_POLIS, no_polis); //These Fields should be your String values of actual column names
        cv.put(TMP_NILAI_PREMI, nilai_premi); //These Fields should be your String values of actual column names
        cv.put(TMP_NILAI_PERTANGGUNGAN, nilai_pertanggungan); //These Fields should be your String values of actual column names
        cv.put(TMP_TGL_MULAI, tgl_mulai); //These Fields should be your String values of actual column names
        cv.put(TMP_TGL_AKHIR, tgl_akhir); //These Fields should be your String values of actual column names
        cv.put(VC_NAMA, nama_u); //These Fields should be your String values of actual column names
        cv.put(VC_JK, jk_u); //These Fields should be your String values of actual column names
        cv.put(VC_TGL_LAHIR, tgl_lahir_u); //These Fields should be your String values of actual column names
        cv.put(VC_USIA, usia_u); //These Fields should be your String values of actual column names
        cv.put(VC_KTP, ktp_u); //These Fields should be your String values of actual column names
        cv.put(VC_TELP, telp_u); //These Fields should be your String values of actual column names
        cv.put(VC_AHLI_WARIS, ahli_waris_u); //These Fields should be your String values of actual column names
        cv.put(VC_HUBUNGAN, hubungan_u); //These Fields should be your String values of actual column names
        cv.put(VC_REFERAL, referal_u); //These Fields should be your String values of actual column names
        cv.put(VC_KODE_POS, kode_pos_u); //These Fields should be your String values of actual column names
        cv.put(VC_PROV, prov_u); //These Fields should be your String values of actual column names
        cv.put(VC_KAB, kab_u); //These Fields should be your String values of actual column names
        cv.put(VC_KEC, kec_u); //These Fields should be your String values of actual column names
        cv.put(VC_FOTO_NS, foto_ns); //These Fields should be your String values of actual column names
        cv.put(VC_FOTO_KTP, foto_ktp); //These Fields should be your String values of actual column names
        cv.put(VC_FOTO_TKD, foto_tkd); //These Fields should be your String values of actual column names
        cv.put(VC_FOTO_TKB, foto_tkb); //These Fields should be your String values of actual column names

        return db.update(TB_VOUCHER, cv, VC_ID + "=" + id, null);
    }

}
