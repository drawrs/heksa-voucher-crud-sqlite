package com.khilman.www.heksavoucher.model

/**
 * Created by root on 11/13/17.
 */

class PolisModel {
    var no_polis: String ? = null
    var nilai_premi: String ? = null
    var nilai_pertanggungan: String ? = null
    var tgl_mulai: String ? = null
    var tgl_akhir: String ? = null

//    constructor(no_polis: String?, nilai_premi: String?, nilai_pertanggungan: String?, tgl_mulai: String?, tgl_akhir: String?) {
//        this.no_polis = no_polis
//        this.nilai_premi = nilai_premi
//        this.nilai_pertanggungan = nilai_pertanggungan
//        this.tgl_mulai = tgl_mulai
//        this.tgl_akhir = tgl_akhir
//    }


    override fun toString(): String {
        return "Data : no polis" + no_polis + " " +
                "\nnilai_premi " + nilai_premi + "" +
                "\nnilai pertanggungan " + nilai_pertanggungan + " " +
                "\ntgl mulai " + tgl_mulai + " " +
                "\ntgl akhir " + tgl_akhir
    }
}
