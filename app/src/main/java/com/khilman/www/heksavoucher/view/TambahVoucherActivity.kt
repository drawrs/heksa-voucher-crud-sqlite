package com.khilman.www.heksavoucher.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Toast
import com.khilman.www.heksavoucher.MainActivity
import com.khilman.www.heksavoucher.R
import com.khilman.www.heksavoucher.database.DatabaseHelper
import com.khilman.www.heksavoucher.model.PolisModel
import kotlinx.android.synthetic.main.activity_tambah_voucher.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TambahVoucherActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, tahun: Int, bulan: Int, tanggal: Int) {
        //        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when(date_type){
            "tgl_awal" -> {
                etTglMulai.editText?.setText("" + tahun + "-" + (bulan + 1) + "-" + tanggal)
            }
            "tgl_akhir" -> {
                etTglAkhir.editText?.setText("" + tahun + "-" + (bulan + 1) + "-" + tanggal)
            }
            "tgl_lahir" -> {

                etTglLahir.editText?.setText("" + tahun + "-" + (bulan + 1) + "-" + tanggal)
                // dapatkan tahun sekarang
                val curr_year : Int = Calendar.getInstance().get(Calendar.YEAR)
                // hitung umur otomatis
                val usia = curr_year - tahun
                if (usia < 5 || usia > 59){
                    alert {
                        message = "Usia pertanggungan Hanya pada Usia 5 - 59 Tahun"

                        yesButton {  }
                        return@alert
                    }.show()
                }
                etUserUmur.editText?.setText(usia.toString())
                // Cek kalo kurang dari 5 tahun maka alert

            }
        }
    }
    var date_type : String ? = null
    var database : DatabaseHelper? = DatabaseHelper(this)
    var listData : List<PolisModel> ? = null
    var fotoNasabah: String ? = "NO_Image.jpg"
    var fotoKtpNasabah: String ? = "NO_Image.jpg"
    var fotoTiketDepan: String ? = "NO_Image.jpg"
    var fotoTiketBelakang: String ? = "NO_Image.jpg"
    var compressedImage : Bitmap? = null
    private val RESULT_LOAD_IMG = 12
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_voucher)
        // Request permission
        requestPermission()
        // set value for spinner
        var adapterJK : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.kelamin_array, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapterJK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // apply adapter to the spinner
        spJenisKel.adapter = adapterJK

        // set value for spinner
        var adapterHB : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.hubungan_array, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapterHB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // apply adapter to the spinner
        spHubungan.adapter = adapterHB

        //
        //  etTglMulai.
        //

        btnSave.setOnClickListener{
            saveVoucher();
        }
        // set on text change listener
        // addTextChangedListener
        etNoPolis.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.d("sblm per", "" + s)

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("ktk per", "" + s)
                // get data dari tb_voucher berdasarkan no polis
                listData = database?.getDataPolis(s.toString())

                Log.d("data", "" + listData?.toString())


                if (!listData?.isEmpty()!!){
                    Log.d("data", "" + listData?.get(0)?.tgl_akhir)
                    var dataPolis = listData?.get(0)
                    etNilaiPremi.editText?.setText(dataPolis?.nilai_premi)
                    etNilaiPertanggungan.editText?.setText(dataPolis?.nilai_pertanggungan)
                    etTglMulai.editText?.setText(dataPolis?.tgl_mulai)
                    etTglAkhir.editText?.setText(dataPolis?.tgl_akhir)

                    etNilaiPremi.editText?.isEnabled = false
                    etNilaiPertanggungan.editText?.isEnabled = false
                    etTglMulai.editText?.isEnabled = false
                    etTglAkhir.editText?.isEnabled = false
                } else {
                    etNilaiPremi.editText?.setText("")
                    etNilaiPertanggungan.editText?.setText("")
                    etTglMulai.editText?.setText("")
                    etTglAkhir.editText?.setText("")

                    etNilaiPremi.editText?.isEnabled = true
                    etNilaiPertanggungan.editText?.isEnabled = true
                    etTglMulai.editText?.isEnabled = true
                    etTglAkhir.editText?.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable) {
                Log.d("sth per", "" + s)
            }
        })
        // Pilih foto
        pilihFotoNs.setOnClickListener{
            pickImage(1)
            //pickImage(2)
        }
        pilihFotoKtp.setOnClickListener{
            pickImage(2)
        }
        pilihFotoTiketDp.setOnClickListener{
            pickImage(3)
        }
        pilihFotoTiketBl.setOnClickListener{
            pickImage(4)
        }
        // lihat foto
        lihatFotoNs.setOnClickListener{
            showImage(1)
        }
        // lihat foto
        lihatFotoKtp.setOnClickListener{
            showImage(2)
        }
        // lihat foto
        lihatFotoTiketDp.setOnClickListener{
            showImage(3)
        }
        // lihat foto
        lihatFotoTiketBl.setOnClickListener{
            showImage(4)
        }
        //showDatePicker()
        // Tampil Date dialog
        etTglMulai.editText?.setOnClickListener{
            date_type = "tgl_awal"
            showDatePicker()
        }
        etTglAkhir.editText?.setOnClickListener{
            date_type = "tgl_akhir"
            showDatePicker()
        }
        etTglLahir.editText?.setOnClickListener {
            date_type = "tgl_lahir"
            showDatePicker()
        }
    }

    private val RC_CAMERA_AND_LOCATION: Int ? = 1

    private fun requestPermission() {
        // Do not have permissions, request them now
        val perms = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing

            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "Membutuhkan akses kamera & penyimpanan",
                    1, *perms)
        }
    }

    @AfterPermissionGranted(1)
    private fun methodRequiresTwoPermission() {
        val perms = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "membutuhkan akses kamera & penyimpanan",
                    1, *perms)
            //EasyPermissions.requestPermissions()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
    fun showImage(i : Int){
        val alert = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.view_image, null)
        alert.setView(dialogView)
        var imageView : ImageView = dialogView.findViewById(R.id.tampilFoto)
        // siapkan gambar
        var imgFile : File? = null


        when(i){
            1 -> imgFile = File(Environment.getExternalStorageDirectory().toString()
                    + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files/$fotoNasabah")
            2 -> imgFile = File(Environment.getExternalStorageDirectory().toString()
                    + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files/$fotoKtpNasabah")
            3 -> imgFile = File(Environment.getExternalStorageDirectory().toString()
                    + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files/$fotoTiketDepan")
            4 -> imgFile = File(Environment.getExternalStorageDirectory().toString()
                    + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files/$fotoTiketBelakang")
        }
        if (imgFile?.exists()!!)
        {
            val myBitmap = BitmapFactory.decodeFile(imgFile?.absolutePath)
            imageView.setImageBitmap(myBitmap)
        }
        // alert.setTitle("Foto Nasabah")
        alert.show()
    }
    fun pickImage(i : Int){
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, i)
    }
    private fun saveVoucher() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var no_polis = etNoPolis.editText?.text.toString()
        var nilai_premi = etNilaiPremi.editText?.text.toString()
        var nilai_pertgn = etNilaiPertanggungan.editText?.text.toString()
        var tgl_mulai = etTglMulai.editText?.text.toString()
        var tgl_akhir = etTglAkhir.editText?.text.toString()

        var nm_tertanggung = etNamaUser.editText?.text.toString()
        var jk = spJenisKel.selectedItem.toString()
        var tgl_lahir = etTglLahir.editText?.text.toString()
        var umur = etUserUmur.editText?.text.toString()

        var no_ktp = etNoKTP.editText?.text.toString()
        var no_hp = etNoTelp.editText?.text.toString()
        var ahli_waris = etAhliWaris.editText?.text.toString()
        var hubungan = spHubungan.selectedItem.toString()
        var kode_ref = etKodeRef.editText?.text.toString()
        var kode_pos = etKodePos.editText?.text.toString()
        var prov = etProv.editText?.text.toString()
        var kec = etKecamatan.editText?.text.toString()
        var kab = etKab.editText?.text.toString()

        if(no_polis.isNotEmpty() &&
                nilai_premi.isNotEmpty() &&
                nilai_pertgn.isNotEmpty() &&
                tgl_mulai.isNotEmpty() &&
                tgl_akhir.isNotEmpty() &&
                nm_tertanggung.isNotEmpty() &&
                jk.isNotEmpty() &&
                tgl_lahir.isNotEmpty() &&
                umur.isNotEmpty() &&
                no_ktp.isNotEmpty() &&
                no_hp.isNotEmpty() &&
                ahli_waris.isNotEmpty() &&
                hubungan.isNotEmpty() &&
                kode_ref.isNotEmpty() &&
                kode_pos.isNotEmpty() &&
                prov.isNotEmpty() &&
                kab.isNotEmpty() &&
                kec.isNotEmpty()){
            // Simpan ke table
            var id : Long ? = database?.insert_tb_voucher(no_polis, nilai_premi, nilai_pertgn, tgl_mulai, tgl_akhir,
                    nm_tertanggung, jk, tgl_lahir, umur, no_ktp,
                    no_hp, ahli_waris, hubungan, kode_ref, kode_pos,
                    prov, kab, kec, fotoNasabah, fotoKtpNasabah, fotoTiketDepan, fotoTiketBelakang)
            // tampilkan di log
            if (id!! <= 0){
                toast("Gagal menambahkan data")
            } else {
                toast("Data berhasil ditambahkan")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            Log.d("Id", "" + id)
        } else {
            toast("Semua input Wajib diisi !")
        }
//        String no_polis,
//        String nilai_premi,
//        String nilai_pertanggungan,
//        String tgl_mulai,
//        String tgl_akhir,
//        String nama_u,
//        String jk_u,
//        String tgl_lahir_u,
//        String usia_u,
//        String ktp_u,
//        String telp_u,
//        String ahli_waris_u,
//        String hubungan_u,
//        String referal_u,
//        String kode_pos_u,
//        String prov_u,
//        String kab_u,
//        String kec_u,
//        String foto_ns,
//        String foto_ktp,
//        String foto_tkd,
//        String foto_tkb
        // input ke sqlite


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            try {
                val imageUri = data?.getData()

                val imageStream = contentResolver.openInputStream(imageUri!!)
                val selectedImage = BitmapFactory.decodeStream(imageStream)

                //selectedImage.compress(Bitmap.CompressFormat.PNG, 70,fos)
                //val d : Drawable = BitmapDrawable(resources, selectedImage)

                //cdKtpNasabah.setBackgroundResource()
                //saveToInternalStorage(selectedImage);
                storeImage(selectedImage, requestCode)
                // cek dia pilih apa
                when(requestCode){
                    1 -> {
                        cdFotoNs.setText(fotoNasabah)
                       // plus_1.visibility = View.INVISIBLE
                    }
                    2 -> {
                        cdKtpNasabah.setText(fotoKtpNasabah)
                        //plus_2.visibility = View.INVISIBLE
                    }
                    3 -> {
                        cdTiketDp.setText(fotoTiketDepan)
                        //plus_3.visibility = View.INVISIBLE
                    }
                    4 -> {
                        cdTiketBl.setText(fotoTiketBelakang)
                        //plus_4.visibility = View.INVISIBLE
                    }

                }
                //mImage.setImageBitmap(selectedImage)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }



    fun storeImage(image: Bitmap, requestCode: Int){
        var pictureFile : File = getOutPutMediaFile(requestCode)

        if (pictureFile == null) {
            Log.d("DADA",
                    "Error creating media file, check storage permissions: ")// e.getMessage());
            return
        }
        try {
            val fos = FileOutputStream(pictureFile)


            image.compress(Bitmap.CompressFormat.PNG, 50, fos)
            //compressedImage = image
            fos.close()
        } catch (e: FileNotFoundException) {
            Log.d("DADA", "File not found: " + e.message)
        } catch (e: IOException) {
            Log.d("DADA", "Error accessing file: " + e.message)
        }

    }



    private fun getOutPutMediaFile(requestCode: Int): File {

        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var exStorage = Environment.getExternalStorageDirectory()
        var mediaStorageDir = File("$exStorage/Android/data/"
                + applicationContext.packageName
                + "/Files")
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        var mediaFile : File ?
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()) {
                return null!!
            }
        }

        var timeStamp : String = SimpleDateFormat("ddMMyyyy_HHmm_ss").format(Date())

        var mImageName : String = "MI_$timeStamp.jpg"
        Log.d("Foto", ""+mImageName)
        mediaFile = File(mediaStorageDir.getPath() + File.separator + mImageName);

        when(requestCode){
            1 -> {
                fotoNasabah = mImageName
            }
            2 -> {
                fotoKtpNasabah = mImageName
            }
            3 -> {
                fotoTiketDepan = mImageName
            }
            4 -> {
                fotoTiketBelakang = mImageName
            }

        }
        return mediaFile
    }

    private fun showDatePicker() {
        val tahun = Calendar.getInstance().get(Calendar.YEAR)
        val bulan = Calendar.getInstance().get(Calendar.MONTH)
        val tanggal = Calendar.getInstance().get(Calendar.DATE)
        val datePicker = DatePickerDialog(this, this@TambahVoucherActivity, tahun, bulan, tanggal)
        datePicker.show()
    }

}
