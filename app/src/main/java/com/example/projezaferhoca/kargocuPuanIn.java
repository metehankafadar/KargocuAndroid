package com.example.projezaferhoca;

public class kargocuPuanIn {

    private String saglamlik,guven,hiz,sonpuan,cnt;

    public kargocuPuanIn(String saglamlik, String guven, String hiz, String sonpuan, String cnt ){
        this.saglamlik = saglamlik;
        this.guven = guven;
        this.hiz = hiz;
        this.sonpuan = sonpuan;
        this.cnt = cnt;
    }

    public String getSaglamlik() {
        return saglamlik;
    }

    public void setSaglamlik(String saglamlik) {
        this.saglamlik = saglamlik;
    }

    public String getGuven() {
        return guven;
    }

    public void setGuven(String guven) {
        this.guven = guven;
    }

    public String getHiz() {
        return hiz;
    }

    public void setHiz(String hiz) {
        this.hiz = hiz;
    }


    public String getSonpuan() {
        return sonpuan;
    }

    public void setSonpuan(String sonpuan) {
        this.sonpuan = sonpuan;
    }


    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}
