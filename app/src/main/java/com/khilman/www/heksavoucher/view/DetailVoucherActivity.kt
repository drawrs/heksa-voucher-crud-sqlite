package com.khilman.www.heksavoucher.view

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.khilman.www.heksavoucher.R
import kotlinx.android.synthetic.main.activity_detail_voucher.*
import java.io.File

class DetailVoucherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_voucher)

//        intent.putExtra("no_ps", "10101010")
//        intent.putExtra("premi", voucher.getNilai_premi())
//        intent.putExtra("uang_pertanggungan", voucher.getNilai_pertanggungan())
//        intent.putExtra("tgl_mulai", voucher.getTgl_mulai())
//        intent.putExtra("tgl_selesai", voucher.getTgl_akhir())
//        intent.putExtra("nama", voucher.getNama_u())
//        intent.putExtra("jk", voucher.getJk_u())
//        intent.putExtra("tgl_lahir", voucher.getTgl_lahir_u())
//        intent.putExtra("usia", voucher.getUsia_u())
//        intent.putExtra("no_ktp", voucher.getKtp_u())
//        intent.putExtra("no_telp", voucher.getTelp_u())
//        intent.putExtra("ahli_waris", voucher.getAhli_waris_u())
//        intent.putExtra("hubungan_tertangung", voucher.getHubungan_u())
//        intent.putExtra("kd_ref", voucher.getReferal_u())
//        intent.putExtra("kd_pos", voucher.getKode_pos_u())
//        intent.putExtra("kd_prov", voucher.getProv_u())
//        intent.putExtra("kd_kab", voucher.getKab_u())
//        intent.putExtra("kd_kec", voucher.getKec_u())
        var intent = getIntent()
        tvNilaiPremi.setText(intent.getStringExtra("premi"))
        tvTglMulai.setText(intent.getStringExtra("tgl_mulai"))
        tvTglAkhir.setText(intent.getStringExtra("tgl_selesai"))
        tvNamaUser.setText(intent.getStringExtra("nama"))
        tvJenisKelamin.setText(intent.getStringExtra("jk"))
        tvTglLahir.setText(intent.getStringExtra("tgl_lahir"))
        tvUsia.setText(intent.getStringExtra("usia"))
        tvNoKtp.setText(intent.getStringExtra("no_ktp"))
        tvNoTelp.setText(intent.getStringExtra("no_telp"))
        tvAhliWaris.setText(intent.getStringExtra("ahli_waris"))
        tvHubungan.setText(intent.getStringExtra("hubungan_tertangung"))
        tvKdRef.setText(intent.getStringExtra("kd_ref"))
        tvKdPos.setText(intent.getStringExtra("kd_pos"))
        tvProv.setText(intent.getStringExtra("kd_prov"))
        tvKab.setText(intent.getStringExtra("kd_kab"))
        tvKec.setText(intent.getStringExtra("kd_kec"))

        var fotoNasabah = intent.getStringExtra("ft_nas").toString()
        var fotoKtp = intent.getStringExtra("ft_ktp").toString()
        var fotoTiketDp = intent.getStringExtra("ft_tk_dp").toString()
        var fotoTiketBl = intent.getStringExtra("ft_tk_bl")


        cdaFotoNs.setText(fotoNasabah)
        cdKtpaNasabah.setText(fotoKtp)
        cdaTiketDp.setText(fotoTiketDp)
        cdaTiketBl.setText(fotoTiketBl)

//

//        var layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true);
//        filesList.layoutManager = layoutManager
//
//        filesList.adapter = AttachementAdapter(this)

//        tvNoPolis.setText()
//        tvNilaiPremi
//        tvTglMulai
//        tvTglAkhir
//        tvNamaUser

//        tvUsia
//        tvNoKtp
//        tvNoTelp
//        tvAhliWaris
//        tvHubungan
//        tvKdRef
//        tvKdPos
//        tvProv
//        tvKec
//        tvKab

// lihat foto
        lihatFotoNsa.setOnClickListener{
            showImage(fotoNasabah)
        }
        // lihat foto
        lihatFotoKtpa.setOnClickListener{
            showImage(fotoKtp)
        }
        // lihat foto
        lihatFotoTiketDpa.setOnClickListener{
            showImage(fotoTiketDp)
        }
        // lihat foto
        lihatFotoTiketBla.setOnClickListener{
            showImage(fotoTiketBl)
        }

    }
    fun showImage(foto : String){
        val alert = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.view_image, null)
        alert.setView(dialogView)
        var imageView : ImageView = dialogView.findViewById(R.id.tampilFoto)
        // siapkan gambar
        var imgFile : File? = null


        imgFile = File(Environment.getExternalStorageDirectory().toString()
                    + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files/$foto")
        if (imgFile?.exists()!!)
        {
            val myBitmap = BitmapFactory.decodeFile(imgFile?.absolutePath)
            imageView.setImageBitmap(myBitmap)
        }
        // alert.setTitle("Foto Nasabah")
        alert.show()
    }
}
