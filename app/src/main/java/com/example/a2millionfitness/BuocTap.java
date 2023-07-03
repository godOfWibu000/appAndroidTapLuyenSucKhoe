package com.example.a2millionfitness;

public class BuocTap {
    private int maBuocTap, maBaiTap;
    private String noiDung, ghiChu, anh;

    public BuocTap(int maBuocTap, int maBaiTap, String noiDung, String ghiChu, String anh) {
        this.maBuocTap = maBuocTap;
        this.maBaiTap = maBaiTap;
        this.noiDung = noiDung;
        this.ghiChu = ghiChu;
        this.anh = anh;
    }

    public int getMaBuocTap() {
        return maBuocTap;
    }

    public int getMaBaiTap() {
        return maBaiTap;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getAnh() {
        return anh;
    }
}
