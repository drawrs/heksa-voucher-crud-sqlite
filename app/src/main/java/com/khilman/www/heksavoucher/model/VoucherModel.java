package com.khilman.www.heksavoucher.model;

/**
 * Created by root on 11/13/17.
 */

public class VoucherModel {
    String _id;
    String no_polis;
    String nilai_premi;
    String nilai_pertanggungan;
    String tgl_mulai;
    String tgl_akhir;
    String nama_u;
    String jk_u;
    String tgl_lahir_u;
    String usia_u;
    String ktp_u;
    String telp_u;
    String ahli_waris_u;
    String hubungan_u;
    String referal_u;
    String kode_pos_u;
    String prov_u;
    String kab_u;
    String kec_u;
    String foto_ns;
    String foto_ktp;
    String foto_tkd;
    String foto_tkb;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNo_polis() {
        return no_polis;
    }

    public void setNo_polis(String no_polis) {
        this.no_polis = no_polis;
    }

    public String getNilai_premi() {
        return nilai_premi;
    }

    public void setNilai_premi(String nilai_premi) {
        this.nilai_premi = nilai_premi;
    }

    public String getNilai_pertanggungan() {
        return nilai_pertanggungan;
    }

    public void setNilai_pertanggungan(String nilai_pertanggungan) {
        this.nilai_pertanggungan = nilai_pertanggungan;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_akhir() {
        return tgl_akhir;
    }

    public void setTgl_akhir(String tgl_akhir) {
        this.tgl_akhir = tgl_akhir;
    }

    public String getNama_u() {
        return nama_u;
    }

    public void setNama_u(String nama_u) {
        this.nama_u = nama_u;
    }

    public String getJk_u() {
        return jk_u;
    }

    public void setJk_u(String jk_u) {
        this.jk_u = jk_u;
    }

    public String getTgl_lahir_u() {
        return tgl_lahir_u;
    }

    public void setTgl_lahir_u(String tgl_lahir_u) {
        this.tgl_lahir_u = tgl_lahir_u;
    }

    public String getUsia_u() {
        return usia_u;
    }

    public void setUsia_u(String usia_u) {
        this.usia_u = usia_u;
    }

    public String getKtp_u() {
        return ktp_u;
    }

    public void setKtp_u(String ktp_u) {
        this.ktp_u = ktp_u;
    }

    public String getTelp_u() {
        return telp_u;
    }

    public void setTelp_u(String telp_u) {
        this.telp_u = telp_u;
    }

    public String getAhli_waris_u() {
        return ahli_waris_u;
    }

    public void setAhli_waris_u(String ahli_waris_u) {
        this.ahli_waris_u = ahli_waris_u;
    }

    public String getHubungan_u() {
        return hubungan_u;
    }

    public void setHubungan_u(String hubungan_u) {
        this.hubungan_u = hubungan_u;
    }

    public String getReferal_u() {
        return referal_u;
    }

    public void setReferal_u(String referal_u) {
        this.referal_u = referal_u;
    }

    public String getKode_pos_u() {
        return kode_pos_u;
    }

    public void setKode_pos_u(String kode_pos_u) {
        this.kode_pos_u = kode_pos_u;
    }

    public String getProv_u() {
        return prov_u;
    }

    public void setProv_u(String prov_u) {
        this.prov_u = prov_u;
    }

    public String getKab_u() {
        return kab_u;
    }

    public void setKab_u(String kab_u) {
        this.kab_u = kab_u;
    }

    public String getKec_u() {
        return kec_u;
    }

    public void setKec_u(String kec_u) {
        this.kec_u = kec_u;
    }

    public String getFoto_ns() {
        return foto_ns;
    }

    public void setFoto_ns(String foto_ns) {
        this.foto_ns = foto_ns;
    }

    public String getFoto_ktp() {
        return foto_ktp;
    }

    public void setFoto_ktp(String foto_ktp) {
        this.foto_ktp = foto_ktp;
    }

    public String getFoto_tkd() {
        return foto_tkd;
    }

    public void setFoto_tkd(String foto_tkd) {
        this.foto_tkd = foto_tkd;
    }

    public String getFoto_tkb() {
        return foto_tkb;
    }

    public void setFoto_tkb(String foto_tkb) {
        this.foto_tkb = foto_tkb;
    }

    @Override
    public String toString() {
        return "Data : no polis" + no_polis + " " +
                "\nnilai_premi " + nilai_premi + "" +
                "\nnilai pertanggungan " + nilai_pertanggungan + " " +
                "\ntgl mulai " + tgl_mulai + " " +
                "\ntgl akhir " + tgl_akhir + " " +
                "\ntgl akhir " + nama_u+ " " +
                "\ntgl akhir " + jk_u+ " " +
                "\ntgl akhir " + tgl_lahir_u+ " " +
                "\ntgl akhir " + usia_u + " " +
                "\ntgl akhir " + ktp_u + " " +
                "\ntgl akhir " + telp_u + " " +
                "\ntgl akhir " + ahli_waris_u + " " +
                "\ntgl akhir " + hubungan_u + " " +
                "\ntgl akhir " + referal_u + " " +
                "\ntgl akhir " + kode_pos_u + " " +
                "\ntgl akhir " + prov_u + " " +
                "\ntgl akhir " + kab_u + " " +
                "\ntgl akhir " + kec_u + " " +
                "\ntgl akhir " + foto_ns + " " +
                "\ntgl akhir " + foto_ktp + " " +
                "\ntgl akhir " + foto_tkd + " " +
                "\ntgl akhir " + foto_tkb+"";
    }
}
