package com.example.modulul7;

public class ItemImagine {
    private String descriere;
    private String urlImagine;
    private String urlWeb;

    public ItemImagine(String descriere, String urlImagine, String urlWeb) {
        this.descriere = descriere;
        this.urlImagine = urlImagine;
        this.urlWeb = urlWeb;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getUrlImagine() {
        return urlImagine;
    }

    public String getUrlWeb() {
        return urlWeb;
    }
}