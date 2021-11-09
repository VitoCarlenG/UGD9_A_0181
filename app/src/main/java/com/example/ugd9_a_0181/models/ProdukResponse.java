package com.example.ugd9_a_0181.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProdukResponse {

    // TODO: Tambahkan atribut sesuai response dari API yang ada di soal, dan juga
    //  buat getter dan setternya.

    private String message;

    @SerializedName("produk")
    private List<Produk> produkList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Produk> getProdukList() {
        return produkList;
    }

    public void setProdukList(List<Produk> produkList) {
        this.produkList = produkList;
    }
}
