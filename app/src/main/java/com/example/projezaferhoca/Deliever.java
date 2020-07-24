package com.example.projezaferhoca;

public class Deliever {
    //title,image,kilo,uzunluk,genislik,status,gönderen,alıcı,kargocu,kargocinsi
    private String title,image,kilo,uzunluk,genislik,status,gonderen,alici,kargocu,kargocinsi,id;

    public Deliever(String title, String image, String kilo, String uzunluk, String genislik, String status, String gonderen, String alici, String kargocu, String kargocinsi,String id) {
        this.title = title;
        this.image = image;
        this.kilo = kilo;
        this.uzunluk = uzunluk;
        this.genislik = genislik;
        this.status = status;
        this.gonderen = gonderen;
        this.alici = alici;
        this.kargocu = kargocu;
        this.kargocinsi = kargocinsi;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKilo() {
        return kilo;
    }

    public void setKilo(String kilo) {
        this.kilo = kilo;
    }

    public String getUzunluk() {
        return uzunluk;
    }

    public void setUzunluk(String uzunluk) {
        this.uzunluk = uzunluk;
    }

    public String getGenislik() {
        return genislik;
    }

    public void setGenislik(String genislik) {
        this.genislik = genislik;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGonderen() {
        return gonderen;
    }

    public void setGonderen(String gonderen) {
        this.gonderen = gonderen;
    }

    public String getAlici() {
        return alici;
    }

    public void setAlici(String alici) {
        this.alici = alici;
    }

    public String getKargocu() {
        return kargocu;
    }

    public void setKargocu(String kargocu) {
        this.kargocu = kargocu;
    }

    public String getKargocinsi() {
        return kargocinsi;
    }

    public void setKargocinsi(String kargocinsi) {
        this.kargocinsi = kargocinsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
