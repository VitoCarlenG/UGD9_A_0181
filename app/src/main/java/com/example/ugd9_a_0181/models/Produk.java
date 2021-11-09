package com.example.ugd9_a_0181.models;

public class Produk {

    private Long id;
    private String nama;
    private Integer stok;
    private String deskripsi;
    private String gambar;
    private Integer harga;

    public Produk(String nama, Integer stok, String deskripsi, String gambar, Integer harga) {
        this.nama = nama;
        this.stok = stok;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.harga = harga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
