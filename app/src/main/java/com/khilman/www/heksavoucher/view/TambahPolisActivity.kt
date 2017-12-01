package com.khilman.www.heksavoucher.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.DatePicker
import com.khilman.www.heksavoucher.MainActivity
import com.khilman.www.heksavoucher.R
import com.khilman.www.heksavoucher.database.DatabaseHelper
import kotlinx.android.synthetic.main.activity_tambah_polis.*
import org.jetbrains.anko.toast
import java.util.*

class TambahPolisActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener  {
    override fun onDateSet(view: DatePicker?, tahun: Int, bulan: Int, tanggal: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when(date_type){
            "tgl_awal" -> {
                etTglMulai.editText?.setText("" + tahun + "-" + (bulan + 1) + "-" + tanggal)
            }
            "tgl_akhir" -> {
                etTglAkhir.editText?.setText("" + tahun + "-" + (bulan + 1) + "-" + tanggal)
            }
        }
    }
    var date_type : String ? = null
    var database : DatabaseHelper ? = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_polis)
        // set onCLikraja
        btnSave.setOnClickListener {
            // tampung ke variable
            var no_polis = etNoPolis.editText?.text
            var nilai_premi = etNilaiPremi.editText?.text
            var nilai_pertanggungan = etNilaiPertanggungan.editText?.text
            var tgl_mulai = etTglMulai.editText?.text
            var tgl_akhir = etTglAkhir.editText?.text

            //toast("$no_polis $nilai_premi $nilai_pertanggungan $tgl_mulai $tgl_akhir")
            toast("Berhasil ditambahkan")

            startActivity(Intent(this, MainActivity::class.java))
            // input ke sqlite
            var id : Long ? = database?.insert_tmp_polis(no_polis.toString(),
                    nilai_premi.toString(),
                    nilai_pertanggungan.toString(),
                    tgl_mulai.toString(),
                    tgl_akhir.toString())
            // tampilkan di log
            Log.d("Id", "" + id)
        }
        // Tampil Date dialog
        etTglMulai.editText?.setOnClickListener{
            date_type = "tgl_awal"
            showDatePicker()
        }
        etTglAkhir.editText?.setOnClickListener{
            date_type = "tgl_akhir"
            showDatePicker()
        }
    }
    private fun showDatePicker() {
        val tahun = Calendar.getInstance().get(Calendar.YEAR)
        val bulan = Calendar.getInstance().get(Calendar.MONTH)
        val tanggal = Calendar.getInstance().get(Calendar.DATE)
        val datePicker = DatePickerDialog(this, this@TambahPolisActivity, tahun, bulan, tanggal)
        datePicker.show()
    }
}
