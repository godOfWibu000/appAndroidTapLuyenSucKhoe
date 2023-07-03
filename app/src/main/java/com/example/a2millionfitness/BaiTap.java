package com.example.a2millionfitness;

public class BaiTap {
    private int maBaiTap, maKeHoach;

    private String tenBaiTap, noiDung, ghiChu, anh;

    public BaiTap(int maBaiTap, int maKeHoach, String tenBaiTap, String noiDung, String ghiChu, String anh) {
        this.maBaiTap = maBaiTap;
        this.maKeHoach = maKeHoach;
        this.tenBaiTap = tenBaiTap;
        this.noiDung = noiDung;
        this.ghiChu = ghiChu;
        this.anh = anh;
    }

    public int getMaBaiTap() {
        return maBaiTap;
    }

    public int getMaKeHoach() {
        return maKeHoach;
    }

    public String getTenBaiTap() {
        return tenBaiTap;
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

    public void setMaBaiTap(int maBaiTap) {
        this.maBaiTap = maBaiTap;
    }

    public void setMaKeHoach(int maKeHoach) {
        this.maKeHoach = maKeHoach;
    }

    public void setTenBaiTap(String tenBaiTap) {
        this.tenBaiTap = tenBaiTap;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
