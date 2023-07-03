package com.example.a2millionfitness;

public class KeHoachCaNhan {
    private int maKeHoach, soNgayHoanThanh;

    private String ten,tenKeHoach, ghiChu, anh;

    public KeHoachCaNhan(int maKeHoach, int soNgayHoanThanh, String ten, String tenKeHoach, String ghiChu, String anh) {
        this.maKeHoach = maKeHoach;
        this.soNgayHoanThanh = soNgayHoanThanh;
        this.ten = ten;
        this.tenKeHoach = tenKeHoach;
        this.ghiChu = ghiChu;
        this.anh = anh;
    }

    public int getMaKeHoach() {
        return maKeHoach;
    }

    public int getSoNgayHoanThanh() {
        return soNgayHoanThanh;
    }

    public String getTen() {
        return ten;
    }

    public String getTenKeHoach() {
        return tenKeHoach;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getAnh() {
        return anh;
    }

    public void setMaKeHoach(int maKeHoach) {
        this.maKeHoach = maKeHoach;
    }

    public void setSoNgayHoanThanh(int soNgayHoanThanh) {
        this.soNgayHoanThanh = soNgayHoanThanh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTenKeHoach(String tenKeHoach) {
        this.tenKeHoach = tenKeHoach;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
